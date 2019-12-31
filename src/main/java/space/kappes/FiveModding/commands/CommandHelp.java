package space.kappes.FiveModding.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;
import space.kappes.FiveModding.util.Colors;

import java.util.Arrays;
import java.util.stream.Collectors;

import static space.kappes.FiveModding.util.EmbedUtil.error;

public class CommandHelp extends Command {

    public CommandHelp() {
        super(new String[]{"help", "h", "?"}, CommandCategory.UTILITY, "[command]", "Display a list of all commands");
    }

    @Override
    public Result execute(CommandEvent commandEvent, String[] args) {
        if (args.length == 0) {
            EmbedBuilder builder = new EmbedBuilder()
                    .setColor(Colors.RED)
                    .setTitle("Command list")
                    .setFooter("For detailed command help you can use fv!help <command>", commandEvent.getGuild().getSelfMember().getUser().getAvatarUrl());
            Arrays.stream(CommandCategory.class.getEnumConstants()).filter(category -> !category.equals(CommandCategory.BOT_OWNER)).forEach(category -> {
                String commandAliases = getCommandAliasByCategory(category);
                if (!commandAliases.equals(""))
                    builder.addField(category.getDisplayName(), commandAliases, false);
            });
            return send(builder);
        } else {
            if (!FiveBot.getInstance().getFiveBot().getCommandManager().getCommandMap().containsKey(args[0].toLowerCase()))
                return send(error("Not found", "The requested command could not found"));
            Command command = FiveBot.getInstance().getFiveBot().getCommandManager().getCommandMap().get(args[0].toLowerCase());
            return command.sendHelp(commandEvent);
        }
    }

    private String getCommandAliasByCategory(CommandCategory category) {
        StringBuilder builder = new StringBuilder();
        FiveBot.getInstance().getFiveBot().getCommandManager().getCommandMap().values().parallelStream().filter(command -> command.getCommandCategory().equals(category)).distinct().collect(Collectors.toList()).forEach(command -> builder.append("`").append(command.getInvocations()[0]).append("`, "));
        if (builder.toString().contains(","))
            builder.replace(builder.lastIndexOf(","), builder.lastIndexOf(",") + 1, "");
        return builder.toString();
    }
}

