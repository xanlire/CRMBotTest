/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

/**
 *
 * @author HP
 */
public abstract class ITextView implements View{
    @Override
    public final ReplyKeyboard getKeyboard(){return null;}
}
