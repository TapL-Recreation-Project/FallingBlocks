package me.swipez.fallingblocks;

import me.swipez.fallingblocks.commands.CommandComplete;
import me.swipez.fallingblocks.commands.StartCommand;
import me.swipez.fallingblocks.listeners.PlayerMineListener;
import me.swipez.fallingblocks.runnables.SpawnFallingBlocks;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public final class FallingBlocks extends JavaPlugin {

    public boolean gamestarted = false;

    public HashMap<UUID, Integer> blocksBroken = new HashMap<>();
    public HashMap<UUID, Integer> fallingBlockCount = new HashMap<>();

    @Override
    public void onEnable() {
        SpawnFallingBlocks.initList();
        BukkitTask SpawnFallingBlocks = new SpawnFallingBlocks(this).runTaskTimer(this, 20, 20);
        getServer().getPluginManager().registerEvents(new PlayerMineListener(this), this);

        getCommand("blockrain").setExecutor(new StartCommand(this));
        getCommand("blockrain").setTabCompleter(new CommandComplete());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
