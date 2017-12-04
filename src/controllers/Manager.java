package controllers;

import model.repositories.*;

import org.telegram.telegrambots.api.methods.send.SendMessage;

public class Manager {
    
    private SendMessage sendMessage;
    private Command previousCommand = Command.NONE, 
                    currentCommand = Command.NONE,
                    nextCommand = Command.NONE;
    private final InputManager inputManager;
    private final ViewManager viewManager;
    private final BillManager billManager;

    public InputManager getInputManager() {
        return inputManager;
    }

    public Manager(){
        this.inputManager = new InputManager();
        this.viewManager = new ViewManager();
        this.billManager = new BillManager();
    }
    
    public void run(){
        commandSwitch();
        updateBill();
        commandExec();
    }
    
    public SendMessage getSendMessage() {
    //TODO: create exceptions for empty contollers
        sendMessage = new SendMessage();
        sendMessage.setChatId(inputManager.getChatId());
        sendMessage.setParseMode("markdown");        
        sendMessage.setReplyMarkup(viewManager.getKeyboard());
        sendMessage.setText(viewManager.getTextMessage());
        return sendMessage;
    }
    
    private void commandSwitch(){
        if(currentCommand != null) previousCommand = currentCommand;
        if((currentCommand = inputManager.getCommand()) == Command.NONE) currentCommand = nextCommand;
    }
    
    private void commandExec(){
        
        switch(currentCommand){
            case START:   
                viewManager.setReplyKeyboard();
                viewManager.setTextMessage("Welcome to the Mr. Mokko CRM system.\n"
                        + "Press 'Add Position' button add new drink to the bill.\n"
                        + "Every time you want to add new coffee, you should press the button");
                break;
                
            case ADD_POSITION:
                billManager.createBill();
                viewManager.setTextMessage("Choose coffee position");
                viewManager.setInlineKeyboard(Positions.getInstance());
                nextCommand = Command.ADD_VOLUME;
                break;
                
            case ADD_VOLUME:        
                viewManager.setTextMessage("Choose volume");
                Volumes.getInstance().setCurrentListByPosition(inputManager.getFirstParsedData());
                viewManager.setInlineKeyboard(Volumes.getInstance());                        
                nextCommand = Command.PRINT_BILL;
                break;
                
            case PRINT_BILL:
                viewManager.setTextMessage(billManager.getBillasString());
                viewManager.setReplyKeyboard();
                break;
                
            case SELL:
                billManager.sell();
                billManager.clearBill();
                viewManager.setTextMessage("Sold!");
                break;
                
            case DELETE_POSITION:
                billManager.deleteByIndex(Integer.parseInt(inputManager.getFirstParsedData()));
                viewManager.setTextMessage(billManager.getBillasString());
                break;
                
            case OPEN:
                break;
//                billManager.
                
        }        
    }
    
    private void updateBill(){        
        switch(previousCommand){
            case ADD_POSITION:
                billManager.addPositionToBill(inputManager.getFirstParsedData());
                break;
            case ADD_VOLUME:
                billManager.addVolumeToBill(inputManager.getFirstParsedData());            
        }        
    }
}
