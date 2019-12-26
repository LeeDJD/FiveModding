package space.kappes.FiveModding.net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.kappes.FiveModding.FiveBot;
import space.kappes.FiveModding.event.impl.shardmanager.ShardManagerMessageReceivedEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ShardManagerHandler implements Runnable {

    private final Logger logger = LogManager.getLogger(getClass());
    private final Socket client;
    private final Scanner inputScanner;
    private final PrintWriter outputWriter;
    private final Thread readThread;
    private boolean alreadyClosed = false;
    private long ping = 0L;

    public ShardManagerHandler() throws IOException {
        this.client = new Socket();
        this.inputScanner = new Scanner(client.getInputStream());
        this.outputWriter = new PrintWriter(client.getOutputStream());
        this.readThread = new Thread(this, "ReadingThread");
        readThread.start();
    }

    @Override
    public void run() {
        while (client.isConnected() && !client.isClosed() && !client.isInputShutdown() && !client.isOutputShutdown()) {
            try {
                if (inputScanner.hasNextLine())
                    FiveBot.getInstance().getEventManager().call(new ShardManagerMessageReceivedEvent(inputScanner.nextLine()));
            }catch (IllegalStateException e) {
                logger.error("Error while reading InputStream of ShardManager", e);
                break;
            }
        }
        try {
            close("Connection timed out");
        } catch (IOException e) {
            logger.error("Error while closing ShardManagerHandler", e);
        }
    }

    public void close(String reason) throws IOException {
        if (alreadyClosed)
            return;
        alreadyClosed = true;
        inputScanner.close();
        outputWriter.close();
        client.close();
        logger.debug(String.format("[ShardManagerHandler] Shutting down with reason %s", reason));
        //TODO: Fire event
    }

    public void close() throws IOException {
        close("No reason specified");
    }

    public Socket getClient() {
        return client;
    }

    public long getPing() {
        return ping;
    }

    public void setPing(long ping) {
        this.ping = ping;
    }
}
