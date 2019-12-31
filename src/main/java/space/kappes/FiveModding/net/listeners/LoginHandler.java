package space.kappes.FiveModding.net.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.event.ListenerAdapter;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerConnectedEvent;

public class LoginHandler extends ListenerAdapter {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void onShardManagerConnected(ShardManagerConnectedEvent event) {
        event.getShardManagerHandler().sendCommand("login", new JSONObject().put("name", FiveBot.getInstance().getConfigManager().getShardName()).put("secret", FiveBot.getInstance().getConfigManager().getAuthSecret()));
    }
}
