package space.kappes.FiveModding.command;

public enum  CommandCategory {

    UNKNOWN(0, "Unknown"),
    SIMPLE(1, "Simple Responses"),
    BOT_OWNER(2, "BotOwner"),
    UTILITY(3, "Utility");


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
