package it.craftopoly.co_staff;

import it.craftopoly.co_staff.executor.*;
import it.craftopoly.co_staff.listener.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public final class CO_Staff extends JavaPlugin {

    public static ArrayList<UUID> vanishedUsers = new ArrayList<>();

    private static CO_Staff plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        saveDefaultConfig();
        getCommand("ban").setExecutor(new BanExecutor());
        getCommand("unban").setExecutor(new UnbanExecutor());
        getCommand("mute").setExecutor(new MuteExecutor());
        getCommand("unmute").setExecutor(new UnmuteExecutor());
        getCommand("teleport").setExecutor(new TeleportExecutor());
        getCommand("chat").setExecutor(new ChatExecutor());
        getCommand("vanish").setExecutor(new VanishExecutor());
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CO_Staff getInstance()
    {
        return plugin;
    }
}
