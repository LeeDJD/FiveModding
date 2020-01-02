package space.kappes.FiveModding.commands.install;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;

public class CommandCodewalker extends Command {

    public CommandCodewalker() {
        super(new String[]{"codewalker","code","walker"}, CommandCategory.INSTALL, "", "Instructions on how to install Codewalker, the singe most important modding tool");
    }

    @Override
    public Result execute(CommandEvent event, String[] args) {
        return send(new EmbedBuilder()
            .setTitle("Codewalker download and basic introduction. The most versatile GTAV modding tool!")
            .setDescription("Codewalker is an interactive GTAV 3D Map. You can select objects in the world edit them move them and much more.")
            .addField("Download","Codewalker can be downloaded on the [codewalker discord](https://discord.gg/BxfKHkk) in the #realses channel", true)
            .addField("Tutorials","Some tutorials can be found on [dexy's YT](https://www.youtube.com/channel/UCvig3BG9Bje3--CbcX3FvJQ/videos) ,and some basic instructions can be found in the readme, but the best way is to simply play around with it!", true)
            .setThumbnail("https://img.gta5-mods.com/q85-w800/images/codewalker-gtav-interactive-3d-map/99b39e-cw35.png")
        );
    }
}
