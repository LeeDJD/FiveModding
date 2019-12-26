package space.kappes.FiveModding.net.command;

import org.json.JSONObject;
import space.kappes.FiveModding.net.ShardManagerHandler;

public abstract class NetCommand {

    private final String invoke;
    private final String description;
    private String[] requiredArgs = new String[]{};

    public NetCommand(String invoke, String description, String[] requiredArgs) {
        this.invoke = invoke;
        this.description = description;
        this.requiredArgs = requiredArgs;
    }

    public NetCommand(String invoke, String description) {
        this.invoke = invoke;
        this.description = description;
    }

    public abstract void run(ShardManagerHandler shardManagerHandler, JSONObject args);

    public String getInvoke() {
        return invoke;
    }

    public String getDescription() {
        return description;
    }

    public String[] getRequiredArgs() {
        return requiredArgs;
    }
}
