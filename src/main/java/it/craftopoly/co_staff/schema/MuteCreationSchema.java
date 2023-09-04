package it.craftopoly.co_staff.schema;

public class MuteCreationSchema {

    private String username;
    private String time;
    private String reason;

    public MuteCreationSchema(String username, String time, String reason) {
        this.username = username;
        this.time = time;
        this.reason = reason;
    }

}