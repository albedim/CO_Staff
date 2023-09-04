package it.craftopoly.co_staff.executor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.craftopoly.co_staff.CO_Staff;
import it.craftopoly.co_staff.utils.HttpCall;
import it.craftopoly.co_staff.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class ChatExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equals("chat")) {
            if (args.length > 1) {
                if (args[0].equals("staff")) {
                    String message = String.join(" ", Arrays.copyOfRange(args, 1
                            , args.length));
                    Object res = HttpCall.getStafferChat(player.getUniqueId().toString());

                    if (res instanceof String)
                        player.sendMessage(res.toString());
                    else {
                        JsonArray staffers = ((JsonArray) res).getAsJsonArray();
                        staffers.forEach(e -> {
                            Player staffer = Bukkit.getPlayer(e.getAsJsonObject().get("username").getAsString());
                            if (staffer != null)
                                if (staffer.isOnline())
                                    staffer.sendMessage(" §8[§a§lStaff§8] §8▪ §7" + player.getName() + " §8➜ §7" + message);
                        });
                    }
                }
            }
        }
        return false;
    }
}
