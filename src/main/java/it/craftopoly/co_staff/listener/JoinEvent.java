package it.craftopoly.co_staff.listener;

import it.craftopoly.co_staff.CO_Staff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;
import java.util.Objects;

public class JoinEvent implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();

        CO_Staff.vanishedUsers.forEach(user -> {
            player.hidePlayer(CO_Staff.getInstance(), Objects.requireNonNull(Bukkit.getPlayer(user)));
        });
    }
}
