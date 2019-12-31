package space.kappes.FiveModding.event.impl.shardmanager;

public class ShardManagerDisconnectedEvent extends ShardManagerEvent {

    private final String reason;

    public ShardManagerDisconnectedEvent(String reason) {
        super();
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
