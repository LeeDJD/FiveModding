package space.kappes.FiveModding.net.commands;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.net.ShardManagerHandler;
import space.kappes.FiveModding.net.command.NetCommand;

import java.util.Timer;
import java.util.TimerTask;

public class NetStopCommand extends NetCommand {

    private final Logger logger = LogManager.getLogger(getClass());

    public NetStopCommand() {
        super("stop", "Stop Bot instances", new String[]{"message"});
    }

    @Override
    public void run(ShardManagerHandler shardManagerHandler, JSONObject args) {
        if (!FiveBot.getInstance().getFiveBot().isConnected()) {
            logger.warn("[ShardManager] Received stop command but bot has already suspended");
            return;
        }
        logger.info(String.format("[ShardManager] Stopping Bot instances with reason: %s", args.getString("message")));
        FiveBot.getInstance().getFiveBot().getShardManager().setPresence(OnlineStatus.DO_NOT_DISTURB, Activity.playing(args.getString("message")));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                FiveBot.getInstance().getFiveBot().getShardManager().shutdown();
                FiveBot.getInstance().getFiveBot().setConnected(false);
            }
        }, 5000);
    }
}
