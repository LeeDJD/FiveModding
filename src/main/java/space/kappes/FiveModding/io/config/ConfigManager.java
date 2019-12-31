package space.kappes.FiveModding.io.config;

import org.json.JSONObject;

public class ConfigManager {

    private final Config config;

    public ConfigManager(Config config) {
        this.config = config;
        setDefaults();
    }

    private void setDefaults() {
        JSONObject botProperty = new JSONObject();
        botProperty.put("token", "DiscordToken");
        config.setDefault("bot", botProperty);
        JSONObject shardManagerProperty = new JSONObject();
        shardManagerProperty.put("secret", "AuthSecret");
        shardManagerProperty.put("name", "Gerda");
        shardManagerProperty.put("host", "http://localhost");
        shardManagerProperty.put("port", 1024);
        config.setDefault("shardmanager", shardManagerProperty);
    }

    public String getBotToken() {
        return config.getJSONObject("bot").getString("token");
    }

    public String getShardName() {
        return config.getJSONObject("shardmanager").getString("name");
    }

    public String getAuthSecret() {
        return config.getJSONObject("shardmanager").getString("secret");
    }

    public int getPort() {
        return config.getJSONObject("shardmanager").getInt("port");
    }

    public String getHost() {
        return config.getJSONObject("shardmanager").getString("host");
    }

}
