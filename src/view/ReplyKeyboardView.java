/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

/**
 *
 * @author HP
 */
public class ReplyKeyboardView extends IKeybordView{
    
    protected final List<String> replyKeyboardList = Arrays.asList("Add Position", "Additions");
    
    @Override
    public ReplyKeyboard getKeyboard(){
        
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
            replyKeyboardList.forEach((buttonName) -> {
                row.add(buttonName);
        });
        keyboard.add(row);
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setResizeKeyboard(Boolean.TRUE);
        
        return replyKeyboardMarkup;    
    }
}
