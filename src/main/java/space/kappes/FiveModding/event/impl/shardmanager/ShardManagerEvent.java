package space.kappes.FiveModding.event.impl.shardmanager;

import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.event.Event;
import space.kappes.FiveModding.net.ShardManagerHandler;

public class ShardManagerEvent extends Event {

    private final ShardManagerHandler shardManagerHandler;

    public ShardManagerEvent() {
        this.shardManagerHandler = FiveBot.getInstance().getShardManagerHandler();
    }

    public ShardManagerHandler getShardManagerHandler() {
        return shardManagerHandler;
    }
}
