package space.kappes.FiveModding.net.command;

import org.json.JSONObject;
import space.kappes.FiveModding.event.ListenerAdapter;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerMessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class NetCommandManager extends ListenerAdapter {

    private final Map<String, NetCommand> commandMap;

    public NetCommandManager() {
        commandMap = new HashMap<>();
    }

    @Override
    public void onShardManagerMessageReceived(ShardManagerMessageReceivedEvent event) {
        String invoke = event.getMessage().split("\\s")[0];
        if (!commandMap.containsKey(invoke))
            return;
        String rawJSON = event.getMessage().replace(invoke + " ", "");
        JSONObject jsonObject = new JSONObject(rawJSON);
        NetCommand command = commandMap.get(invoke);
        for (String required : command.getRequiredArgs()) {
            if (!jsonObject.has(required)) {
                event.getShardManagerHandler().sendCommand("error", new JSONObject().put("type", "Invalid Command").put("message", String.format("Missing argument: %s", required)));
                return;
            }
        }
        command.run(event.getShardManagerHandler(), jsonObject);
    }

    public void registerCommand(NetCommand command) {
        commandMap.put(command.getInvoke(), command);
    }

    public void registerCommands(NetCommand... commands) {
        for (NetCommand command : commands)
            registerCommand(command);
    }

}
