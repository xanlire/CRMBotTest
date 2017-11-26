package controllers;

import org.telegram.telegrambots.api.objects.Update;
import utils.Parser;

public class InputManager {
    
    private Long chatId = null;
    private String data = null;
    private Parser parser;    
    
    public Long getChatId(){                       
        return chatId;
    }

    public String getData() {
        return data;
    }

    public void setUpdate(Update update) {
                
        if(update.hasMessage()){
           this.chatId = update.getMessage().getChatId();
           this.data = update.getMessage().getText();
        }   
        if(update.hasCallbackQuery()){
            this.chatId = update.getCallbackQuery().getMessage().getChatId();
            this.data = update.getCallbackQuery().getData();
        }
        if(update.hasEditedMessage()){
            this.chatId = update.getEditedMessage().getChatId();
        }        
                
        parser = new Parser(data);
    }
    
//    public String getNextArgument(){
//        return parser.getTokenList().get(parser.getPointer());
//    }
    public String getFirstParsedData(){
        return parser.getTokenList().get(0);
    }
    
    public String getParsedDataByIndex(int index){
        return parser.getTokenList().get(index);
    }
        
    public Command getCommand(){
        if(parser.isSlash() && Character.isDigit(getFirstParsedData().charAt(0)))
            return Command.DELETE_POSITION;
        switch(getFirstParsedData()){
            case "start" : return Command.START;
            case "Add": return Command.ADD_POSITION;
            case "Cancel": return Command.CANCEL_POSITION;
            case "Sell": return Command.SELL;
            case "open": return Command.OPEN;
            case "close": return Command.CLOSE;            
            default: return Command.NONE;
        }        
    }
}
