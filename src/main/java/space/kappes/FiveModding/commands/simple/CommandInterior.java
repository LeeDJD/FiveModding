package space.kappes.FiveModding.commands.simple;

import net.dv8tion.jda.api.EmbedBuilder;
import space.kappes.FiveModding.command.Command;
import space.kappes.FiveModding.command.CommandCategory;
import space.kappes.FiveModding.command.CommandEvent;
import space.kappes.FiveModding.command.Result;

public class CommandInterior extends Command {


    public CommandInterior() {
        super(new String[]{"interior", "mlo"}, CommandCategory.SIMPLE, "", "Get some information on how to begin with creating/editing MLO Interiors");
    }

    @Override
    public Result execute(CommandEvent event, String[] args) {
        return send(new EmbedBuilder()
            .setTitle("Here is an entry point into making GTAV MLO interiors")
            .setDescription("Tarkayne made some good Tutorial on his [YT Channel](https://www.youtube.com/channel/UCtZPE7rGoXB4EH3InV_Y7sg/videos) and on his [discord](https://discordapp.com/invite/wH99bBQ)")
            .addField("**Beginner**", "As mentioned above. I ordered them into a [playlist](https://www.youtube.com/playlist?list=PL99g137sYzbHezU98SE7HCLyXTPq1lKT8)", false)
            .addField("**Experienced**", "DEKURWINATOR also made some excellent tutorials, but they are harder to understand [LINK](https://www.youtube.com/watch?v=0vbRGOBhGxg&list=PLHN6Tkyea9EZoP05JxpwT8RlSIE3_h1D0)", false)
        );
    }
}
