package space.kappes.FiveModding.commands.simple;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;

public class CommandYMAP extends Command {

    public CommandYMAP() {
        super(new String[]{"ymap", "fixmymod", "fmm"}, CommandCategory.SIMPLE, "", "Give simple instructions on why a ymap is not working");
    }

    @Override
    public Result execute(CommandEvent event, String[] args) {
        return send(new EmbedBuilder()
            .setTitle("My YMAP is not working/showing ingame!")
            .addField("**Manifest**","Did you generate a manifest? If not go to your [project window](https://i.imgur.com/2h8DipG.png) and press Tools -> Manifest Generator -> Save _manifest.ymf", false)
            .addField("**Extends**","Did you calculate the extends after making changes to the ymap? This can be done in the [Project Window](https://i.imgur.com/LHMTkWd.png), which is accessible via the toolbar which you can open with t!", false)
            .addField("**DLC**","Do you have DLC enabled in Codewalker? This can be checked on the side panel! For FiveM use [mpchristmas2018](https://i.imgur.com/rm4qFs5.png)", false)
        );
    }
}
