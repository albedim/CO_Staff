package it.craftopoly.co_staff.listener;

import it.craftopoly.co_staff.CO_Staff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class QuitEvent implements Listener
{
    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();
        CO_Staff.vanishedUsers.remove(player.getUniqueId());
    }
}
