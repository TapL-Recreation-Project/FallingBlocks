package me.swipez.fallingblocks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> commands = new ArrayList<>();
            List<String> actuallist = new ArrayList<>();
            commands.add("start");
            commands.add("stop");
            if (args[0].length() == 0){
                return commands;
            }
            for (int i=0; i < commands.size(); i++){
                if (commands.get(i).startsWith(args[0])){
                    actuallist.add(commands.get(i));
                }
            }
            return actuallist;
        }
        if (args.length >= 2){
            List<String> nothing = new ArrayList<>();
            return nothing;
        }
        return null;
    }
}
