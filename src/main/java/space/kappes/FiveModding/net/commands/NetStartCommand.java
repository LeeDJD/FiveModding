package space.kappes.FiveModding.net.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.net.ShardManagerHandler;
import space.kappes.FiveModding.net.command.NetCommand;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class NetStartCommand extends NetCommand {

    private final Logger logger = LogManager.getLogger(getClass());

    public NetStartCommand() {
        super("start", "Start the Bot instances", new String[]{"totalShards", "shardIds"});
    }

    @Override
    public void run(ShardManagerHandler shardManagerHandler, JSONObject args) {
        try {
            FiveBot.getInstance().getFiveBot().connect(args.getInt("totalShards"), getShardIds(args.getJSONArray("shardIds")));
        } catch (LoginException e) {
            logger.error("[ShardManager]Error while starting JDA", e);
        }
    }

    private Integer[] getShardIds(JSONArray array){
        List<Integer> out = new ArrayList<>();
        for(int i = 0; i< array.length(); i++){
            out.add(array.getInt(i));
        }
        return out.toArray(new Integer[0]);

    }

}
