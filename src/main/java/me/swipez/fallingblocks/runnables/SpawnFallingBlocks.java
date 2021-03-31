package me.swipez.fallingblocks.runnables;

import me.swipez.fallingblocks.FallingBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SpawnFallingBlocks extends BukkitRunnable {

    static List<Material> blocks = Arrays.asList(Material.values());

    public static void initList(){
        blocks = blocks.stream().filter((material) -> !(!material.isBlock() || material.isAir())).collect(Collectors.toList());
        blocks.remove(Material.END_GATEWAY);
        blocks.remove(Material.END_PORTAL);
        blocks.remove(Material.NETHER_PORTAL);
    }

    FallingBlocks plugin;

    public SpawnFallingBlocks(FallingBlocks plugin) {
        this.plugin = plugin;
    }

    Random random = new Random();

    @Override
    public void run() {
        if (plugin.gamestarted){
            for (Player player : Bukkit.getOnlinePlayers()){
                if (!plugin.fallingBlockCount.containsKey(player.getUniqueId())) {
                    plugin.fallingBlockCount.put(player.getUniqueId(), 1);
                }
                int loops = plugin.fallingBlockCount.get(player.getUniqueId());
                for (int i = 0; i < loops; i++){
                    Location location = player.getLocation();
                    location.setY(location.getY()+30);
                    int bound = 50;
                    int x = random.nextInt(bound);
                    int z = random.nextInt(bound);
                    int selector = random.nextInt(blocks.size());
                    Material material = blocks.get(selector);
                    float coinflipx = random.nextFloat();
                    float coinflipz = random.nextFloat();
                    if (coinflipx < 0.50){
                        x = x*-1;
                    }
                    if (coinflipz < 0.50){
                        z = z*-1;
                    }
                    location.setX(location.getX()+x);
                    location.setZ(location.getZ()+z);
                    player.getWorld().spawnFallingBlock(location, material.createBlockData());
                }
            }
        }
    }
}
