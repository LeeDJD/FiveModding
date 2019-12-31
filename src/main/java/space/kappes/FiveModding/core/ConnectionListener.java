package space.kappes.FiveModding.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.kappes.FiveModding.event.ListenerAdapter;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerConnectedEvent;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerDisconnectedEvent;

public class ConnectionListener extends ListenerAdapter {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void onShardManagerConnected(ShardManagerConnectedEvent event) {
        logger.info("[ShardManager] Connected to ShardManager!");
    }

    @Override
    public void onShardManagerDisconnected(ShardManagerDisconnectedEvent event) {
        logger.info(String.format("[ShardManager] Disconnected from ShardManager with reason: %s", event.getReason()));
    }
}
