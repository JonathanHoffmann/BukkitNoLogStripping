package me.Jonnyfant.NoLogStripping;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class LogStripListener implements Listener {
    JavaPlugin plugin;

    public LogStripListener(JavaPlugin pin) {
        plugin = pin;
    }

    @EventHandler
    public void onLogStripping(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("axe") || p.getInventory().getItemInOffHand().getType().toString().toLowerCase().contains("axe")) {
            File playerstrippingfile = new File(plugin.getDataFolder(), p.getUniqueId() + ".yml");
            YamlConfiguration playerstrippingConfig = YamlConfiguration.loadConfiguration(playerstrippingfile);
            if (!playerstrippingConfig.getBoolean("current")) {
                ArrayList<Material> woods = new ArrayList<Material>();
                woods.add(Material.OAK_LOG);
                woods.add(Material.OAK_WOOD);
                woods.add(Material.SPRUCE_LOG);
                woods.add(Material.SPRUCE_WOOD);
                woods.add(Material.BIRCH_LOG);
                woods.add(Material.BIRCH_WOOD);
                woods.add(Material.JUNGLE_LOG);
                woods.add(Material.JUNGLE_WOOD);
                woods.add(Material.ACACIA_LOG);
                woods.add(Material.ACACIA_WOOD);
                woods.add(Material.DARK_OAK_LOG);
                woods.add(Material.DARK_OAK_WOOD);
                woods.add(Material.CRIMSON_STEM);
                woods.add(Material.CRIMSON_HYPHAE);
                woods.add(Material.WARPED_STEM);
                woods.add(Material.WARPED_HYPHAE);

                for (int i = 0; i < woods.size(); i++) {
                    if (e.getClickedBlock().getType().equals(woods.get(i))) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.GREEN + "You were just trying to strip a wood/log/stem/hyphae. If this wasn't accidental, you can use /logstripping to toggle stripping on. ;) You can even set the default when logging in with /logstrippingdefault");
                        break;
                    }
                }
            }
        }
    }
}