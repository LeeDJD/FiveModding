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
        botProperty.put("token", System.getenv("SHARD_TOKEN") == null ? "DiscordToken" : System.getenv("SHARD_TOKEN"));
        config.setDefault("bot", botProperty);
        JSONObject shardManagerProperty = new JSONObject();
        shardManagerProperty.put("secret", System.getenv("SHARDMANAGER_SECRET") == null ? "AuthSecret" : System.getenv("SHARDMANAGER_SECRET"));
        shardManagerProperty.put("name", System.getenv("SHARD_NAME") == null ? "Gerda" : System.getenv("SHARD_NAME"));
        shardManagerProperty.put("host", System.getenv("SHARDMANAGER_HOST") == null ? "http://localhost" : System.getenv("SHARDMANAGER_HOST"));
        shardManagerProperty.put("port", System.getenv("SHARDMANAGER_PORT") == null ? 1024 : Integer.getInteger(System.getenv("SHARDMANAGER_PORT")));
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
