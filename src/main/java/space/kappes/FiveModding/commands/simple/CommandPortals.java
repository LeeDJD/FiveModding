package space.kappes.FiveModding.commands.simple;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;

public class CommandPortals extends Command {

    public CommandPortals() {
        super(new String[]{"portal", "portals"}, CommandCategory.SIMPLE, "", "Get some information on how to create a portal");
    }

    @Override
    public Result execute(CommandEvent event, String[] args) {
        return send(
                new EmbedBuilder()
                .setTitle("How to create a portal")
                .setDescription("Open your MLO's ytyp and go to your portals, then add in the <corners> attribute the corner coordinates which you can get in 3DS Max! **IMPORTANT**: this has to be done for each door in you MLO. The entrance will be from Room 1 to 0(limbo)!")
                .addField("**Tutorial**", "There is a great [tutorial available by Tarkayne](https://www.youtube.com/watch?v=SPzWaZA2AwA)", false)
        );
    }
}
