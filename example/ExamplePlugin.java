package net.runelite.client.plugins.microbot.example;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = PluginDescriptor.Default + "Example",
        description = "Microbot example plugin",
        tags = {"example", "microbot"},
        enabledByDefault = false
)
@Slf4j
public class ExamplePlugin extends Plugin {
    @Inject
    private ExampleConfig config;
    @Provides
    ExampleConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ExampleConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private ExampleOverlay exampleOverlay;

    @Inject
    ExampleScript exampleScript;

    public static boolean attackDelay = false;
    public static int tickCount = 0;


    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(exampleOverlay);
        }
        exampleScript.run(config);
    }

    protected void shutDown() {
        exampleScript.shutdown();
        overlayManager.remove(exampleOverlay);
    }
    int ticks = 10;
    @Subscribe
    public void onGameTick(GameTick tick)
    {

        if (attackDelay) {
            tickCount++;

            if (tickCount >= 3) {
                attackDelay = false; // Reset the boolean after 3 ticks
                tickCount = 0; // Reset the counter
                Microbot.log("3 ticks elapsed. eating attack delay over.");
                // Set your script's boolean or trigger action here
            }
        }

        if (ticks > 0) {
            ticks--;
        } else {
            ticks = 10;
        }

    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        String msg = event.getMessage();

        if (msg.equals("You eat half the apple pie.") ||
                msg.equals("You eat the remaining apple pie.")) {
            attackDelay = true;
            tickCount = 0; // Reset the tick counter
        }
            //Microbot.log("Received message: " + event.getMessage() + " | Type: " + event.getType());
            if (event.getType() == ChatMessageType.GAMEMESSAGE) {
                String message = Text.removeTags(event.getMessage()).toLowerCase();
                if (message.contains("you can't light a fire here.")) {
                    Microbot.log("cannot light fire registered in plugin");
                }

                if (message.equals("you need an axe to chop down this tree.") || message.equals("you do not have an axe which you have the woodcutting level to use.")){
                    Microbot.log("you need an axe message registered");
                }
            }

        Microbot.log("Message received: '" + event.getMessage() + "'");
        if (event.getMessage().toLowerCase().contains("is already in your clan.")) {
            Microbot.log("registered already in clan message");
        }
    }

        }
