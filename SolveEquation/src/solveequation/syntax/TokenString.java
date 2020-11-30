package solveequation.syntax;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class TokenString {
    private List<Token> tokens;
    
    public TokenString(){
        this.tokens = new ArrayList<>();
    }
    
    public TokenString(List<Token> tokens){
        this.tokens = tokens;
    }
    
    public int getLength(){
        return tokens.size();
    }
    
    public Token getIndex(int i){
        return tokens.get(i);
    }
    
    public void addToken(Token x){
        tokens.add(x);
    }
    
    public void insert(int i, Token x){
        tokens.add(i, x);
    }
    
    public void remove (int i){
        tokens.remove(i);
    }
    
    public TokenString split(int start, int stop){
        start = Math.max(0, start);
        stop = Math.min(tokens.size(), stop);
        List<Token>subList = new ArrayList<>();
//        TokenString subList = new TokenString();
        for(int i=start; i<stop; i++){
            subList.add(tokens.get(i));
        }
        return new TokenString(subList);
    }
}
