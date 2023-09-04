package it.craftopoly.co_staff.executor;

import it.craftopoly.co_staff.CO_Staff;
import it.craftopoly.co_staff.utils.HttpCall;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class VanishExecutor implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if(cmd.getName().equals("vanish"))
        {
            if(HttpCall.canCommand("vanish", player.getUniqueId().toString()))
            {
                if(!CO_Staff.vanishedUsers.contains(player.getUniqueId()))
                {
                    CO_Staff.vanishedUsers.add(player.getUniqueId());
                    setVanish(true, player);
                    player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.vanish-active"));
                }else{
                    CO_Staff.vanishedUsers.remove(player.getUniqueId());
                    setVanish(false, player);
                    player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.vanish-unactive"));
                }
            }else {
                player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.no_enough_permissions"));
            }

        }
        return false;
    }

    private static void setVanish(boolean vanish, Player player)
    {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        players.forEach(e -> {
            if(vanish)
                e.hidePlayer(CO_Staff.getInstance(), player);
            else
                e.showPlayer(CO_Staff.getInstance(), player);
            player.setAllowFlight(vanish);
        });
    }
}
