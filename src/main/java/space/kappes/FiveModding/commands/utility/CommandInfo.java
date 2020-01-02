package space.kappes.FiveModding.commands.utility;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;
import space.kappes.FiveModding.util.Colors;

public class CommandInfo extends Command {

    public CommandInfo() {
        super(new String[]{"info","i"}, CommandCategory.UTILITY, "", "Display some basic Information about the Bot");
    }

    @Override
    public Result execute(CommandEvent event, String[] args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(event.getJDA().getSelfUser().getName(), null, event.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Colors.BLUE)
                    .setTitle("Discord Bot for the GTA Mapping/Modding Community", "https://github.com/LeeDJD/FiveModding")
                    .setDescription("I created this bot because I often noticed how often some things are getting repeated over and over again on GTA Modding Discords like the [Codewalker Discord](https://discord.gg/BxfKHkk) and wanted to make the support process simpler and prevent people from getting frustrated by answering the same questions over and over again!")
                    .addField("Code","This bot ist open source and available on my [Github](https://github.com/LeeDJD)",false)
                    .addField("Sources", String.format("As one could think I did not discover any of this stuff and have no clue about most of this. Everything is from the knowledge of the Discord Communities accessible from the %ssources Command!", FiveBot.DEFAULT_PREFIX), false)
                    .setFooter("Created by Leon Kappes | 2019-2020");
        return send(embedBuilder);
    }
}
