package space.kappes.FiveModding.event;

import space.kappes.FiveModding.event.impl.bot.AllShardsInitializedEvent;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerConnectedEvent;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerDisconnectedEvent;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerLoggedInEvent;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerMessageReceivedEvent;

public class ListenerAdapter {

    public void onGenericEvent(Event event) {}

    //ShardManager
    public void onShardManagerMessageReceived(ShardManagerMessageReceivedEvent event) {}
    public void onShardManagerConnected(ShardManagerConnectedEvent event) {}
    public void onShardManagerLoggedIn(ShardManagerLoggedInEvent event) {}
    public void onShardManagerDisconnected(ShardManagerDisconnectedEvent event) {}

    //Bot
    public void onAllShardsInitialized(AllShardsInitializedEvent event) {}

    public final void onEvent(Event event) {
        //ShardManager
        if (event instanceof ShardManagerMessageReceivedEvent)
            this.onShardManagerMessageReceived((ShardManagerMessageReceivedEvent) event);
        else if (event instanceof ShardManagerConnectedEvent)
            this.onShardManagerConnected((ShardManagerConnectedEvent) event);
        else if (event instanceof ShardManagerDisconnectedEvent)
            this.onShardManagerDisconnected((ShardManagerDisconnectedEvent) event);
        else if (event instanceof ShardManagerLoggedInEvent)
            this.onShardManagerLoggedIn((ShardManagerLoggedInEvent) event);

        //Bot
        else if (event instanceof AllShardsInitializedEvent)
            this.onAllShardsInitialized((AllShardsInitializedEvent) event);
    }

}
