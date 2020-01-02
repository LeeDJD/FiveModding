package space.kappes.FiveModding.command;

public enum  CommandCategory {

    UNKNOWN(0, "Unknown"),
    SIMPLE(1, "Simple Responses"),
    FIVEM(2, "FiveM Responses"),
    INSTALL(3, "Installation Responses"),
    BOT_OWNER(4, "BotOwner"),
    UTILITY(5, "Utility");


    private long id;
    private String displayName;

    CommandCategory(long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
