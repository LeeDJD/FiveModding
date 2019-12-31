package space.kappes.FiveModding.command;


import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.command.impl.CommandEventImpl;
import space.kappes.FiveModding.util.EmbedUtil;
import space.kappes.FiveModding.util.SafeMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommandManager extends ListenerAdapter {

    private final Logger logger = LogManager.getLogger(getClass());
    private final Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isWebhookMessage() || event.getAuthor().isBot() || event.getAuthor().isFake() || !event.isFromType(ChannelType.TEXT))
            return;
        handle(event);
    }

    private void handle(MessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw();
        String prefix;
        if (content.startsWith(event.getJDA().getSelfUser().getAsMention()))
            prefix = event.getJDA().getSelfUser().getAsMention();
        else if (content.startsWith(FiveBot.DEFAULT_PREFIX))
            prefix = FiveBot.DEFAULT_PREFIX;
        else
            return;

        String contentNoPrefix = content.substring(prefix.length()).trim();
        String[] rawArgs = contentNoPrefix.split("\\s+");
        String invoke = rawArgs[0].toLowerCase();
        String[] args = new String[rawArgs.length - 1];
        System.arraycopy(rawArgs, 1, args, 0, args.length);
        CommandEventImpl commandEvent = new CommandEventImpl(
                prefix,
                invoke,
                event,
                String.join(" ", args)
        );

        if (commandMap.containsKey(invoke))
            callCommands(commandEvent, commandMap.get(invoke), args);
    }

    private void callCommands(CommandEventImpl commandEvent, Command command, String[] args) {
        if (args.length == 0 || command.getSubCommands().size() == 0)
            executeCommand(commandEvent, command, args);
        else {
            if (command.getSubCommands().containsKey("<*>") || command.getSubCommands().containsKey(args[0])) {
                commandEvent.setInvoke(args[0]);
                String[] newArgs = new String[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, newArgs.length);
                commandEvent.setArgsAsString(String.join(" ", newArgs));
                callCommands(commandEvent, command.getSubCommands().get(args[0]), newArgs);
            } else
                executeCommand(commandEvent, command, args);
        }
    }

    private void executeCommand(CommandEvent event, Command command, String[] args) {
        if (event.getMember().hasPermission(event.getExecutionEvent().getTextChannel(), Permission.MESSAGE_MANAGE))
            event.getMessage().delete().queue();
        Message message = null;
        try {
            message = command.execute(event, args).build();
        } catch (NullPointerException ignored) {
            //occurs when no message was returned
        }
        if (message == null)
            return;
        if (event.getMember().hasPermission(event.getExecutionEvent().getTextChannel(), Permission.MESSAGE_WRITE))
            message = SafeMessage.sendMessageBlocking(event.getTextChannel(), message);
        if (event.getMember().hasPermission(event.getExecutionEvent().getTextChannel(), Permission.MESSAGE_MANAGE))
            message.delete().queueAfter(30, TimeUnit.SECONDS);
    }

    public void registerCommand (Command command){
        Arrays.asList(command.getInvocations()).forEach(invocation -> commandMap.put(invocation.toLowerCase(), command));
    }

    public void unregisterCommand (Command command){
        Arrays.asList(command.getInvocations()).forEach(invocation -> commandMap.remove(invocation.toLowerCase()));
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }
}
