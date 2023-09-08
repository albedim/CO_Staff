package it.craftopoly.co_staff.executor;

import it.craftopoly.co_staff.CO_Staff;
import it.craftopoly.co_staff.utils.HttpCall;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MuteExecutor implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if(cmd.getName().equals("mute"))
        {
            if (args.length > 2) {
                String targetName = args[0];
                String time = args[1];
                String reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                String res = HttpCall.mute(player.getUniqueId().toString(), targetName, time, reason);
                player.sendMessage(res);
            }else{
                player.sendMessage("§8-------------------------------------");
                player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.mute_tutorial"));
                player.sendMessage("§8-------------------------------------");
            }
        }
        return false;
    }
}
