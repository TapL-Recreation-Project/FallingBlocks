package me.swipez.fallingblocks.listeners;

import me.swipez.fallingblocks.FallingBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class PlayerMineListener implements Listener {

    FallingBlocks plugin;

    public PlayerMineListener(FallingBlocks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBreaksBlock(BlockBreakEvent event){
        if (plugin.gamestarted){
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (plugin.blocksBroken.containsKey(uuid)){
                plugin.blocksBroken.put(uuid, plugin.blocksBroken.get(uuid)+1);
                if (plugin.blocksBroken.get(uuid) == 100) {
                    plugin.blocksBroken.put(uuid, 0);
                    if (plugin.fallingBlockCount.containsKey(uuid)) {
                        plugin.fallingBlockCount.put(uuid, plugin.fallingBlockCount.get(uuid) + 1);
                    }
                    player.sendMessage(ChatColor.GRAY+"[!] The rain is getting heavier! Now you'll see "+ChatColor.LIGHT_PURPLE+plugin.fallingBlockCount.get(uuid)+ChatColor.GRAY+" blocks a second...");
                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
                }
            }
            else {
                plugin.blocksBroken.put(uuid, 1);
            }
        }
    }
}
