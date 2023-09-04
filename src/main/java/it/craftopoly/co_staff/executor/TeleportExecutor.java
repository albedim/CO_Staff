package it.craftopoly.co_staff.executor;

import it.craftopoly.co_staff.CO_Staff;
import it.craftopoly.co_staff.utils.HttpCall;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportExecutor implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if(cmd.getName().equals("teleport"))
        {
            if(HttpCall.canCommand("teleport", player.getUniqueId().toString())) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.teleport(target);
                        player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.teleport_success"));
                    }else
                        player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.teleport_failed"));
                } else if (args.length == 2) {
                    try {
                        player.teleport(
                                new Location(
                                        player.getWorld(),
                                        parseCoordinate(args[0]),
                                        player.getLocation().getY(),
                                        parseCoordinate(args[1])
                                )
                        );
                        player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.teleport_success"));
                    } catch (Exception e) {
                        Player playerToTeleport = Bukkit.getPlayer(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(playerToTeleport != null && target != null) {
                            playerToTeleport.teleport(target);
                            player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.teleport_success"));
                        }else
                            player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.teleport_failed"));
                    }
                }else{
                    player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.teleport_tutorial"));
                }
            }else{
                player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.no_enough_permissions"));
            }
        }
        return false;
    }

    private static Double parseCoordinate(String coordinate) throws Exception {
        try{
            return Double.parseDouble(coordinate);
        }catch (Exception e) {
            throw new Exception();
        }
    }
}
