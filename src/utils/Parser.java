package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parser {
    private StringTokenizer tokenizer = null;
    private final String delimeters = " ;/.,";
    private List<String> tokens = new ArrayList<>();
    private int pointer = 0;
    private boolean slash = false;
    
    public Parser(String message){
        tokenizer = new StringTokenizer(trimSlash(message), delimeters);  
        getTokens();
    }
    
    private void getTokens(){
        while(tokenizer.hasMoreTokens()){
            tokens.add(tokenizer.nextToken());
        }
    } 
    
    private String trimSlash(String message){
        if(message.charAt(0) == '/'){
            slash = true;
            return message.substring(1);
        } else {
            return message;
        }            
    }
    
    public List<String> getTokenList(){        
        return this.tokens;
    }    
    
    public boolean isSlash(){return slash;}
}
