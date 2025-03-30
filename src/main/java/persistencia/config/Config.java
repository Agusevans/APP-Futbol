package persistencia.config;

public class Config {
    public static boolean useDataBase = System.getenv().getOrDefault("USE_DB", "false").equals("true");
}