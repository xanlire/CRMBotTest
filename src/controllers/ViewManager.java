/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.repositories.IContainer;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import view.Keyboard;

/**
 *
 * @author HP
 */
public class ViewManager {
    
    private Keyboard keyboard;
    private String textMessage;
    //TODO: create this object in Keyboard class
    private ReplyKeyboard currentKeyboard;
    
    public ViewManager(){
        this.keyboard = new Keyboard();
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
    
    public ReplyKeyboard getKeyboard(){
        return this.currentKeyboard;
    }
    
    public void setReplyKeyboard(){
        this.currentKeyboard = keyboard.getReplyKeyboardMarkup();
    }
    
    public void setInlineKeyboard(IContainer container){
        this.currentKeyboard = keyboard.getInlineKeyboardMarkup(container.getNameList());
    }
    
    
}
