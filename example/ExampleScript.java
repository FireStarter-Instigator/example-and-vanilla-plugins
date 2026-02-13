package net.runelite.client.plugins.microbot.example;

import net.runelite.api.*;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.inventory.Rs2ItemModel;
import net.runelite.client.plugins.microbot.util.npc.Rs2NpcModel;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.trade.Rs2Trade;

import java.util.concurrent.TimeUnit;

import static net.runelite.api.ItemID.STRENGTH_POTION4;
import static net.runelite.api.ItemID.TEAM50_CAPE;
import static net.runelite.api.gameval.ItemID.*;


public class ExampleScript extends Script {


    public boolean run(ExampleConfig config) {
        Microbot.enableAutoRunOn = false;

        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!super.run())
                    return;

                ///  code here

            } catch (Exception ex) {
                System.out.println("Error in login monitor: " + ex.getMessage());
            }

           // Microbot.log("End of login check cycle");
        }, 0, 2000, TimeUnit.MILLISECONDS);

        return true;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}