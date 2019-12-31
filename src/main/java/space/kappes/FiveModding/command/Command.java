package space.kappes.FiveModding.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import space.kappes.FiveModding.util.Colors;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    private final String[] invocations;
    private final CommandCategory commandCategory;
    private final String usage;
    private final String description;
    private final Map<String, Command> subCommands = new HashMap<>();
    private String usageBase;


    public Command(String[] invocations, CommandCategory commandCategory, String usage, String description) {
        if (invocations.length == 0)
            throw new RuntimeException("Invocations cannot be empty.");
        this.invocations = invocations;
        this.commandCategory = commandCategory;
        this.usage = usage;
        this.description = description;
        this.usageBase = invocations[0];
    }

    public abstract Result execute(CommandEvent event, String[] args);

    protected final Result send(String s) {
        return new Result(s);
    }

    protected final Result send(EmbedBuilder embedBuilder) {
        return new Result(embedBuilder);
    }

    protected final Result send(Message message) {
        return new Result(message);
    }

    public final Result sendHelp(CommandEvent commandEvent) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(String.format("Command Help - %s", invocations[0]));
        embedBuilder.setDescription(description);
        embedBuilder.setColor(Colors.BLUE);
        embedBuilder.addField("Usage", generateUsage(this, commandEvent.getPrefix() + usageBase), false);
        embedBuilder.setFooter(String.format("Requested by %s#%s", commandEvent.getAuthor().getName(), commandEvent.getAuthor().getDiscriminator()), commandEvent.getAuthor().getAvatarUrl());
        return send(embedBuilder);
    }

    public final String generateUsage(Command command, String usageBase) {
        StringBuilder usage = new StringBuilder();
        usage.append(usageBase).append(" ").append(command.usage);
        return usage.toString();
    }

    public String[] getInvocations() {
        return invocations;
    }

    public CommandCategory getCommandCategory() {
        return commandCategory;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Command> getSubCommands() {
        return subCommands;
    }

    private String getUsageBase() {
        return usageBase;
    }
}
