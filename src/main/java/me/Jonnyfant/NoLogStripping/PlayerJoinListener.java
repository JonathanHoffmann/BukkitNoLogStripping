package me.Jonnyfant.NoLogStripping;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class PlayerJoinListener implements Listener {
    JavaPlugin plugin;
    public PlayerJoinListener(JavaPlugin pin) {
        plugin=pin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        File playerstrippingfile = new File(plugin.getDataFolder(), p.getUniqueId() + ".yml");
        YamlConfiguration playerstrippingConfig = YamlConfiguration.loadConfiguration(playerstrippingfile);
        if(!playerstrippingfile.exists())
        {
            playerstrippingConfig.set("current", true);
            playerstrippingConfig.set("default", true);
        }
        else {
            playerstrippingConfig.set("current", playerstrippingConfig.getBoolean("default"));
        }
        try{
            playerstrippingConfig.save(playerstrippingfile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}