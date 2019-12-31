package space.kappes.FiveModding.net.command;

import space.kappes.FiveModding.net.commands.NetHeartbeatCommand;
import space.kappes.FiveModding.net.commands.NetStartCommand;
import space.kappes.FiveModding.net.commands.NetStopCommand;

public class NetCommandRegistry {

    public NetCommandRegistry(NetCommandManager netCommandManager) {
        netCommandManager.registerCommands(
            new NetStartCommand(),
            new NetStopCommand(),
            new NetHeartbeatCommand()
        );
    }

}
