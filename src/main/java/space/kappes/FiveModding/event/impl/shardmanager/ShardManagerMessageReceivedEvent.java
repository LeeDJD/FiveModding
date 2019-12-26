package space.kappes.FiveModding.event.impl.shardmanager;

public class ShardManagerMessageReceivedEvent extends ShardManagerEvent {

    private final String message;


    public ShardManagerMessageReceivedEvent(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
