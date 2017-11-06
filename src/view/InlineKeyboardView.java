/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

/**
 *
 * @author HP
 */
public class InlineKeyboardView extends IKeybordView{

    private Map<String, String> buttonNames;

    public void setButtonNames(Map<String, String> buttonNames) {
        this.buttonNames = buttonNames;
    }    
    
    @Override
    public ReplyKeyboard getKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();        
        Iterator<Map.Entry<String, String>> iterator = buttonNames.entrySet().iterator();
        
        for(int i = 0; i < buttonNames.size()/4 + 1; i++){
            
            List<InlineKeyboardButton> keyboardRow = new ArrayList<>();            
            
            for(int j = 0; j < 4 && iterator.hasNext(); j++){   
                InlineKeyboardButton button = new InlineKeyboardButton();
                Map.Entry<String, String> buttonData = iterator.next();
                button.setCallbackData(buttonData.getKey());
                button.setText(buttonData.getValue());
                keyboardRow.add(button);
            }
            keyboard.add(keyboardRow);                
        }              
        return inlineKeyboardMarkup.setKeyboard(keyboard);
    }
    
}
