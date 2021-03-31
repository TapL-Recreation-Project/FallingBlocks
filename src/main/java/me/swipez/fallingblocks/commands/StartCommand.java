package me.swipez.fallingblocks.commands;

import me.swipez.fallingblocks.FallingBlocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    FallingBlocks plugin;

    public StartCommand(FallingBlocks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("blockrain.trigger")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        plugin.gamestarted = true;
                        Bukkit.broadcastMessage(ChatColor.GRAY+"[!] Block rain challenge has "+ChatColor.GREEN+"begun"+ChatColor.GRAY+"!");
                    }
                    else if (args[0].equals("stop")){
                        plugin.gamestarted = false;
                        Bukkit.broadcastMessage(ChatColor.GRAY+"[!] Block rain challenge has "+ChatColor.GREEN+"ended"+ChatColor.GRAY+"!");
                    }
                    else {
                        p.sendMessage(ChatColor.RED+"/blockrain <start/stop>");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED+"/blockrain <start/stop>");
                }
            }
            else {
                p.sendMessage(ChatColor.RED+"You do not have the permission to run this command!");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }
        return true;
    }
}
