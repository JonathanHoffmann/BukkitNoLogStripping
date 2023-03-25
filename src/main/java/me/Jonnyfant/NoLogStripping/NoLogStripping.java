package me.Jonnyfant.NoLogStripping;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class NoLogStripping extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LogStripListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "This command is for players only");
            return false;
        }
        if(command.getName().equals("nologstripping") || command.getName().equals("nlg"))
        {
            toggleLogStripping((Player) sender);
            return true;
        }
        else if (command.getName().equals("nologstrippingdefault"))
        {
            toggleLogStrippingDefault((Player) sender);
            return true;
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Unknown Command");
            return false;
        }
    }

    private void toggleLogStrippingDefault(Player p) {
        File playerdefaultstrippingfile = new File(this.getDataFolder(), p.getUniqueId() + ".yml");
        YamlConfiguration playerdefaultstrippingConfig = YamlConfiguration.loadConfiguration(playerdefaultstrippingfile);
        if(playerdefaultstrippingConfig.getBoolean("default"))
        {
            playerdefaultstrippingConfig.set("default", false);
            p.sendMessage(ChatColor.GREEN + "When logging in, stripping woods will be disabled to save you from accidents. You can always toggle it on and off during a session with /nologstripping.");
        }
        else
        {
            playerdefaultstrippingConfig.set("default", true);
            p.sendMessage(ChatColor.GREEN + "When logging in, you can automatically strip woods. This is like 'Vanilla'. You can still toggle it off with /nologstripping");
        }
        try{
            playerdefaultstrippingConfig.save(playerdefaultstrippingfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toggleLogStripping(Player p) {
        File playerstrippingfile = new File(this.getDataFolder(), p.getUniqueId() + ".yml");
        YamlConfiguration playerstrippingConfig = YamlConfiguration.loadConfiguration(playerstrippingfile);
        if(playerstrippingConfig.getBoolean("current"))
        {
            playerstrippingConfig.set("current", false);
            p.sendMessage(ChatColor.GREEN + "Log Stripping is now disabled.");
        }
        else
        {
            playerstrippingConfig.set("current", true);
            p.sendMessage(ChatColor.GREEN + "Log Stripping is now enabled.");
        }
        try{
            playerstrippingConfig.save(playerstrippingfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}