package crmbottest;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import controllers.*;

import utils.PropertiesInitializer;

public class CRMBotTest extends TelegramLongPollingBot{

    private final Manager manager;
    private final PropertiesInitializer properties;

    {
        manager = new Manager();
        properties = new PropertiesInitializer("res/bot_config.properties");
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new CRMBotTest());
        }catch (TelegramApiException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public String getBotToken() {                
        return properties.getProperty("token");
    }
    
    @Override
    public String getBotUsername() {
        return properties.getProperty("username");
    }
    
    @Override
    public void onUpdateReceived(Update update) {
                
        manager.getInputManager().setUpdate(update);
        manager.run();
        
        try{
            sendMessage(manager.getSendMessage());
        }catch(TelegramApiException ex){
            System.out.println(ex.getMessage() + ex.getClass());
        }
    }    
}