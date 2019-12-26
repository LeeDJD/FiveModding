package space.kappes.FiveModding.event;

import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerMessageReceivedEvent;

public class ListenerAdapter {

    public void onGenericEvent(Event event) {}

    //ShardManager
    public void onShardManagerMessageReceived(ShardManagerMessageReceivedEvent event) {}

    public final void onEvent(Event event) {
        //ShardManager
        if (event instanceof ShardManagerMessageReceivedEvent)
            this.onShardManagerMessageReceived((ShardManagerMessageReceivedEvent) event);

    }

}
