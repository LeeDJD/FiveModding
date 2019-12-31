package space.kappes.FiveModding.net.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import space.kappes.FiveModding.net.ShardManagerHandler;
import space.kappes.FiveModding.net.command.NetCommand;

import java.util.Date;

public class NetHeartbeatCommand extends NetCommand {

    private final Logger logger = LogManager.getLogger(getClass());

    public NetHeartbeatCommand() {
        super("heartbeat", "Handles heartbeat received ShardManager", new String[]{"timestamp"});
    }

    @Override
    public void run(ShardManagerHandler shardManagerHandler, JSONObject args) {
        shardManagerHandler.sendCommand("heartbeat_response", new JSONObject().put("oldTimestamp", args.getLong("timestamp")).put("timestamp", new Date().getTime()));
        logger.debug(String.format("[ShardManager] Received Heartbeat %s", args.toString()));
    }
}
