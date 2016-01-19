package kr.rodumani.minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final static EnglishToKorean englishToKorean2 = new EnglishToKorean2();
    private final static EnglishToKorean englishToKorean3 = new EnglishToKorean3();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent asyncPlayerChatEvent) {
        String message = asyncPlayerChatEvent.getMessage();
        if (message.startsWith("-")) {
            asyncPlayerChatEvent.setMessage(englishToKorean2.engToKor(message.substring(1)));
        }
        else if (message.startsWith("=")) {
            asyncPlayerChatEvent.setMessage(englishToKorean3.engToKor(message.substring(1)));
        }
        else {
            asyncPlayerChatEvent.setMessage(message);
        }
    }
}
