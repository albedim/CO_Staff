package it.craftopoly.co_staff.executor;

import it.craftopoly.co_staff.CO_Staff;
import it.craftopoly.co_staff.utils.HttpCall;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class UnmuteExecutor implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if(cmd.getName().equals("unmute"))
        {
            if (args.length == 1) {
                String target = args[0];
                String res = HttpCall.unmute(player.getUniqueId().toString(), target);
                player.sendMessage(res);
            }else{
                player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.wrong_command_use"));
            }
        }
        return false;
    }
}
