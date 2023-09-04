package it.craftopoly.co_staff.executor;

import com.google.gson.JsonObject;
import it.craftopoly.co_staff.CO_Staff;
import it.craftopoly.co_staff.utils.DateUtils;
import it.craftopoly.co_staff.utils.HttpCall;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class UnbanExecutor implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if(cmd.getName().equals("unban"))
        {
            if (args.length == 1) {
                String username = args[0];
                String res = HttpCall.unban(player.getUniqueId().toString(), username);
                player.sendMessage(res);
            }else{
                player.sendMessage(CO_Staff.getInstance().getConfig().getString("messages.ban_tutorial"));
            }
        }
        return false;
    }

    private String getBanMessage(JsonObject ban)
    {
        return "§a§lCRAFTOPOLY\n\n" +
                "§7Sei stato bannato!\n\n" +
                "§8 ▪ §7Bannato da: §a"+ban.get("banned_by").getAsJsonObject().get("username").getAsString() + "\n" +
                "§8 ▪ §7Bannato il: §a"+ DateUtils.fixDate(ban.get("banned_on").getAsString().split(" ")[0]) +
                " " + ban.get("banned_on").getAsString().split(" ")[1].split(":")[0] + ":" +
                ban.get("banned_on").getAsString().split(" ")[1].split(":")[1] + "\n" +
                "§8 ▪ §7Fine del ban: §a"+ (
                !ban.get("ends_on").getAsString().equals("perma") ? (
                        DateUtils.fixDate(ban.get("ends_on").getAsString().split(" ")[0]) +
                                " " + ban.get("ends_on").getAsString().split(" ")[1].split(":")[0] + ":" +
                                ban.get("ends_on").getAsString().split(" ")[1].split(":")[1]
                ) : "Mai") + "\n" +
                "§8 ▪ §7Motivazione: §a"+ ban.get("reason").getAsString() + "\n\n" +
                "§7Se pensi sia un errore richiedi lo sban\ncreando un ticket sul nostro sito o su discord.\n\n"+
                "§8 ▪ §7§n"+ CO_Staff.getInstance().getConfig().getString("messages.website") + "\n" +
                "§8 ▪ §7§n"+ CO_Staff.getInstance().getConfig().getString("messages.discord");
    }
}
