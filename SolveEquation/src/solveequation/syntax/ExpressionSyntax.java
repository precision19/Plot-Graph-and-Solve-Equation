package solveequation.syntax;
import java.util.List;
import java.util.ArrayList;
import solveequation.expression.*;
/**
 *
 * @author Admin
 */
public class ExpressionSyntax {
    private Error error;
    private Variable x;
    private Variable y;
    private Variable z;
    
    public ExpressionSyntax(){
        error = new Error();
        x = new Variable();
        y = new Variable();
        z = new Variable();
    }
    
    private TokenType getTokenTypeByName(String name){
        TokenType[] values = TokenType.FUNCTIONS;
        for(TokenType v : values){
//            System.out.println(v);
            if(v.name.equals(name)){
//                System.out.println(v.name);

                return v;
            }
        }
        return null;
    }
    
    public Function parse(String exp){
        TokenString tokens = tokenize(exp);
        //if(tokens == null) System.out.println("1");
        //else System.out.println("2");
        if(tokens!=null){
            checkParentheses(tokens);
            substituteNegative(tokens);
            for(int i=0; i<tokens.getLength(); i++){
//                System.out.println(tokens.getIndex(i).type);
            }
            Quantity root = doOrderOfOperations(tokens);
//            System.out.println(root);
//            if(root == null) System.out.println("2");
            if(root == null){
                error.makeError("Parsing function " + exp + " failed!!!");
                return null;
            }
            return new Function(root, x, y, z);
        }
        error.makeError("Parsing function " + exp + " failed!!!");
        return null;
    }
    
    private Quantity doOrderOfOperations(TokenString tokens){
        int location = 0;
        Quantity ret;
        ret = new solveequation.expression.Number(0.0);
        
        location = scanFromRightToLeft(tokens, TokenType.PLUS);
//        for(int i=0; i<tokens.getLength(); i++){
//           System.out.println(tokens.getIndex(i).type);
//        }
//        System.out.println(location + " " + tokens.getLength());
        if(location!=-1){
            TokenString left = tokens.split(0, location);
            TokenString right = tokens.split(location + 1, tokens.getLength());
            ret = new Sum(doOrderOfOperations(left), doOrderOfOperations(right));
            
        }
        else{
            location = scanFromRightToLeft(tokens, TokenType.MINUS);
            if(location!=-1){
                TokenString left = tokens.split(0, location);
                TokenString right = tokens.split(location+1, tokens.getLength());
                ret = new Difference(doOrderOfOperations(left), doOrderOfOperations(right));
            }
            else{
                location = scanFromRightToLeft(tokens, TokenType.DIVIDED_BY);
                if(location!=-1){
                    TokenString left = tokens.split(0, location);
                    TokenString right = tokens.split(location + 1, tokens.getLength());
                    ret = new Quotient(doOrderOfOperations(left), doOrderOfOperations(right));
                }
                else{
                    location = scanFromRightToLeft(tokens, TokenType.TIMES);
                    if(location!=-1){
                        TokenString left = tokens.split(0, location);
                        TokenString right = tokens.split(location + 1, tokens.getLength());
                        ret = new Product(doOrderOfOperations(left), doOrderOfOperations(right));
                    }
                    else{
                        location = scanFromRightToLeft(tokens, TokenType.MODULO);
                        if(location != -1){
                            TokenString left = tokens.split(0, location);
                            TokenString right = tokens.split(location + 1, tokens.getLength());
                            ret = new Modulo(doOrderOfOperations(left), doOrderOfOperations(right));
                        }
                        else{
                            location = scanFromRightToLeft(tokens, TokenType.RAISED_TO);
                            if(location != -1){
                                TokenString left = tokens.split(0, location);
                                TokenString right = tokens.split(location + 1, tokens.getLength());
                                ret = new Power(doOrderOfOperations(left), doOrderOfOperations(right));
                            }
                            else{
                                location = scanFromRightToLeft(tokens, TokenType.FUNCTIONS);
//                                    System.out.println(location);
                                if(location!=-1){
                                    int endParams = getFunctionParamsEnd(tokens, location+2);
//                                        System.out.println(endParams);
                                    if(endParams != -1){
                                        TokenString paramString = tokens.split(location + 2, endParams);
                                        ret = parseFunctionParams(paramString, tokens.getIndex(location).type);
                                    }
                                }
                                else if(tokens.getLength() >= 2 && tokens.getIndex(tokens.getLength() - 1).type == TokenType.CLOSE_PARENTHESES && tokens.getIndex(0).type == TokenType.OPEN_PARENTHESES){
                                    ret = doOrderOfOperations(tokens.split(1, tokens.getLength()-1));
                                }
                                else{
                                    location = scanFromRightToLeft(tokens, TokenType.X);
                                    if(location != -1){
                                        ret = this.x;
                                    }
                                    else{
                                        location = scanFromRightToLeft(tokens, TokenType.Y);
                                        if(location != -1){
                                            ret = this.y;
                                        }
                                        else{
                                            location = scanFromRightToLeft(tokens, TokenType.Z);
                                            if(location != -1){
                                                ret = this.z;
                                            }
                                            else{
                                                location = scanFromRightToLeft(tokens, TokenType.NUMBER);
                                                if(location != -1){
                                                    ret = new solveequation.expression.Number(Double.parseDouble(tokens.getIndex(location).data));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    
    //kiem tra dau ngoac
    private void checkParentheses(TokenString tokens){
        int open = 0;
        for(int i=0; i<tokens.getLength(); i++){
            Token t = tokens.getIndex(i);
            //int open = 0;
            if(t.type == TokenType.OPEN_PARENTHESES){
                open++;
            }
            if(t.type == TokenType.CLOSE_PARENTHESES){
                open--;
            }
            if(open < 0) error.makeError("Too many close parentheses");
        }
        if(open > 0) error.makeError("Too many open parentheses");
    }
    
    
    
    private TokenString tokenize(String exp){
        TokenString tks = new TokenString();
        
        String name = "";
        String number = "";
        //dem so dau '.'
        int numDot = 0;
        for(int i=0; i< exp.length(); i++){
//            System.out.println(name);
            char cur = exp.charAt(i);
            //ki tu dac biet
            boolean spec = false;
            if(Character.isAlphabetic(cur)){
                if(cur == 'x') {
                    tks.addToken(new Token(TokenType.X));
                }
                else if(cur == 'y'){
                    tks.addToken(new Token(TokenType.Y));
                }
                else if(cur == 'z'){
                    tks.addToken(new Token(TokenType.Z));
                }
                else name+=cur;
                spec = true;
            }
            else if(name.length() > 0){
                if(getTokenTypeByName(name) == null){
                    error.makeError("1The function name " + name + " is not valid!!!");
                    return null;
                }
                else {
                    tks.addToken(new Token(getTokenTypeByName(name)));
                    name = "";
                }               
            }
            if(Character.isDigit(cur) || cur == '.'){
                if(cur == '.'){
                    numDot++;
                    number+=cur;
                }
                else number += cur;
                spec = true;
            }
            else if(number.length() > 0){
                if(numDot == 1){
                    if(number.length() <= 2) {
                        error.makeError("2The number " + number + " is not valid!!!");
                        return null;
                    }
                }
                else if(numDot >= 2) {
                    error.makeError("3The number " + number + " is not valid!!!");
                    return null;
                }
                tks.addToken(new Token(TokenType.NUMBER, number));
                number = "";
                numDot = 0;
            }
            if(!(spec || cur == ' ')){
                if(cur == '(') tks.addToken(new Token(TokenType.OPEN_PARENTHESES));
                else if(cur == ')') tks.addToken(new Token(TokenType.CLOSE_PARENTHESES));
                else if(cur == '+') tks.addToken(new Token(TokenType.PLUS));
                else if(cur == '-') tks.addToken(new Token(TokenType.MINUS));
                else if(cur == '/') tks.addToken(new Token(TokenType.DIVIDED_BY));
                else if(cur == '*') tks.addToken(new Token(TokenType.TIMES));
                else if(cur == '^') tks.addToken(new Token(TokenType.RAISED_TO));
                else if(cur == '%') tks.addToken(new Token(TokenType.MODULO));
                else{
                    error.makeError("4The character " + x + " is not suitable!!!");
                    return null;
                }
            }
        }
        //name con lai sau khi duyet xong
        if(name.length() > 0){
            if(getTokenTypeByName(name) == null){
                error.makeError("5The function name " + name + " is not valid!!!");
                return null;
            }
            else{
                tks.addToken(new Token(getTokenTypeByName(name)));
            }
        }

        //number con lai sau khi duyet xong
        if(number.length() > 0){
            if(numDot == 1){
                if(number.length() <= 2) {
                    error.makeError("6The number " + number + " is not valid!!!");
                    return null;
                }
            }
            else if(numDot >= 2) {
                error.makeError("7The number " + number + " is not valid!!!");
                return null;
            }
            tks.addToken(new Token(TokenType.NUMBER, number));
        }
        return tks;
    }
    
    //thay the so am
    private void substituteNegative(TokenString tokens){
        Token prev = null;
        for(int i=0; i<tokens.getLength(); i++){
            Token t = tokens.getIndex(i);
            if(t.type == TokenType.MINUS){
                if(prev == null || !(prev.type == TokenType.CLOSE_PARENTHESES || prev.type == TokenType.NUMBER || prev.type == TokenType.X)){
                // "-" -> "(0-1)*"
                tokens.remove(i);
                tokens.insert(i, new Token(TokenType.TIMES));
                tokens.insert(i, new Token(TokenType.CLOSE_PARENTHESES));
                tokens.insert(i, new Token(TokenType.NUMBER, "1"));
                tokens.insert(i, new Token(TokenType.MINUS));
                tokens.insert(i, new Token(TokenType.NUMBER, "0"));
                tokens.insert(i, new Token(TokenType.OPEN_PARENTHESES));
                i+=6;
                };
            }
            prev = t;
        }
    }
    
    private int scanFromRightToLeft(TokenString tokens, TokenType[] types){
        int open = 0; // so dau mo ngoac don
        for(int i=tokens.getLength() - 1; i >= 0; i--){
            Token t = tokens.getIndex(i);
            if(t.type == TokenType.OPEN_PARENTHESES){
                open--;
            }
            else if(t.type == TokenType.CLOSE_PARENTHESES){
                open++;
            }
            else{
                if(open == 0){
                    for(int j = 0; j < types.length; j ++ ){
                        if(t.type == types[j]) return i;
                    }
                }
            }
        }
        return -1;
    }
    
    private int scanFromRightToLeft(TokenString tokens, TokenType type){
        int open = 0;
        for(int i=tokens.getLength()-1; i>=0; i--){
            Token t = tokens.getIndex(i);
//            System.out.println(t.type);
            if(t.type == TokenType.CLOSE_PARENTHESES) open++;
            else if(t.type == TokenType.OPEN_PARENTHESES) open--;
            else if(open == 0 && t.type == type){
                return i;
            }
        }
        return -1;
    }
    
    private int getFunctionParamsEnd(TokenString tokens, int location){
        int open = 0;
        for(int i = location; i<tokens.getLength(); i++){
            Token t = tokens.getIndex(i);
//            System.out.println(t.type);
            if(t.type == TokenType.OPEN_PARENTHESES){
                open++;
            }
            else if(t.type == TokenType.CLOSE_PARENTHESES){
                if(open == 0) return i;
                open--;
            }
        }
        return -1;
    }
    
    //phan tich cu phap ham tham so
    private Quantity parseFunctionParams(TokenString paramString, TokenType type){
        List<TokenString> params = new ArrayList<>();
        int start = 0;
        for(int i=0; i<paramString.getLength(); i++){
            Token t = paramString.getIndex(i);
            if(t.type == TokenType.COMMA){
                params.add(paramString.split(start, i));
                start = i+1;
            }
        }
        params.add(paramString.split(start, paramString.getLength()));
        
        if (params.size() == 0) return null;
		
        if (params.size() == 1) {
                Quantity param1 = doOrderOfOperations(params.get(0));
                switch (type) {
                case ABSOLUTE_VALUE:
                        return new AbsoluteValue(param1);
                case CEILING:
                        return new Ceiling(param1);
                case FLOOR:
                        return new Floor(param1);
                case SINE:
                        return new Sine(param1);
                case COSINE:
                        return new Cosine(param1);
                case TANGENT:
                        return new Tangent(param1);
                case COTANGENT:
                        return new Cotangent(param1);
                case SECANT:
                        return new Secant(param1);
                case COSECANT:
                        return new Cosecant(param1);
                case SQUARE_ROOT:
                        return new SquareRoot(param1);
                default:
                        return null;
                }
        } else if (params.size() == 2) {
                Quantity param1 = doOrderOfOperations(params.get(0));
                Quantity param2 = doOrderOfOperations(params.get(1));
                switch (type) {
                case NTHROOT:
                        return new NthRoot(param1, param2);
                case LOG:
                        return new Log(param1, param2);
                default:
                        return null;
                }
        }
        return null;
    }
}
    
    
