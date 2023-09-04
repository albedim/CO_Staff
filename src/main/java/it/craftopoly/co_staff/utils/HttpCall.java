package it.craftopoly.co_staff.utils;

import com.google.gson.JsonObject;
import it.craftopoly.co_staff.CO_Staff;
import it.craftopoly.co_staff.schema.MuteCreationSchema;

public class HttpCall
{
    public static Object getStafferChat(String uuid)
    {
        JsonObject res = HttpUtils.get(
                "/users/staffers/chat",
                uuid,
                JsonObject.class
        ).getAsJsonObject();

        if(res.get("code").getAsInt() != 200 && res.get("code").getAsInt() != 403)
            return CO_Staff.getInstance().getConfig().getString("messages.message_failed");

        if(res.get("code").getAsInt() == 403)
            return CO_Staff.getInstance().getConfig().getString("messages.no_chat_enough_permissions");

        return res.get("param").getAsJsonArray();
    }

    public static String mute(String uuid, String target, String time, String reason)
    {
        JsonObject res = HttpUtils.post(
                "/mutes/",
                uuid,
                new MuteCreationSchema(target, time, reason),
                JsonObject.class
        ).getAsJsonObject();

        if(res.get("code").getAsInt() != 409
                && res.get("code").getAsInt() != 200
                && res.get("code").getAsInt() != 403)
            return CO_Staff.getInstance().getConfig().getString("messages.error");

        if(res.get("code").getAsInt() == 403)
            return CO_Staff.getInstance().getConfig().getString("messages.no_enough_permissions");

        if(res.get("code").getAsInt() == 409)
            return CO_Staff.getInstance().getConfig().getString("messages.already_muted");

        return CO_Staff.getInstance().getConfig().getString("messages.muted");
    }

    public static String unmute(String uuid, String username)
    {
        JsonObject res = HttpUtils.put(
                "/mutes/user/" + username,
                uuid,
                null,
                JsonObject.class
        ).getAsJsonObject();

        if(res.get("code").getAsInt() != 403
                && res.get("code").getAsInt() != 200
                && res.get("code").getAsInt() != 400
                && res.get("code").getAsInt() != 404)
            return CO_Staff.getInstance().getConfig().getString("messages.error");

        if(res.get("code").getAsInt() == 403)
            return CO_Staff.getInstance().getConfig().getString("messages.no_enough_permissions");

        if(res.get("code").getAsInt() == 400)
            return CO_Staff.getInstance().getConfig().getString("messages.wrong_command_use");

        if(res.get("code").getAsInt() == 404)
            return CO_Staff.getInstance().getConfig().getString("messages.user_not_found");

        return CO_Staff.getInstance().getConfig().getString("messages.unmuted");
    }

    public static String unban(String uuid, String username)
    {
        JsonObject res = HttpUtils.put(
                "/bans/user/" + username,
                uuid,
                null,
                JsonObject.class
        ).getAsJsonObject();

        if(res.get("code").getAsInt() != 403
                && res.get("code").getAsInt() != 200
                && res.get("code").getAsInt() != 400
                && res.get("code").getAsInt() != 404)
            return CO_Staff.getInstance().getConfig().getString("messages.error");

        if(res.get("code").getAsInt() == 403)
            return CO_Staff.getInstance().getConfig().getString("messages.no_enough_permissions");

        if(res.get("code").getAsInt() == 400)
            return CO_Staff.getInstance().getConfig().getString("messages.wrong_command_use");

        if(res.get("code").getAsInt() == 404)
            return CO_Staff.getInstance().getConfig().getString("messages.user_not_found");

        return CO_Staff.getInstance().getConfig().getString("messages.unbanned");
    }

    public static Object ban(String uuid, String target, String time, String reason)
    {
        JsonObject res = HttpUtils.post(
                "/bans/",
                uuid,
                new MuteCreationSchema(target, time, reason),
                JsonObject.class
        ).getAsJsonObject();

        if(res.get("code").getAsInt() != 409
                && res.get("code").getAsInt() != 200
                && res.get("code").getAsInt() != 400
                && res.get("code").getAsInt() != 403)
            return CO_Staff.getInstance().getConfig().getString("messages.error");

        if(res.get("code").getAsInt() == 403)
            return CO_Staff.getInstance().getConfig().getString("messages.no_enough_permissions");

        if(res.get("code").getAsInt() == 400)
            return CO_Staff.getInstance().getConfig().getString("messages.wrong_command_use");

        if(res.get("code").getAsInt() == 409)
            return CO_Staff.getInstance().getConfig().getString("messages.already_banned");

        return res;
    }

    public static Boolean canCommand(String command, String uuid)
    {
        JsonObject res = HttpUtils.get(
                "/users/staffers/command/"+command,
                uuid,
                JsonObject.class
        ).getAsJsonObject();
        System.out.println(uuid);
        return res.get("code").getAsInt() == 200 && res.get("param").getAsBoolean();
    }
}

