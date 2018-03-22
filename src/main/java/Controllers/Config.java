package Controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_PATH = System.getProperty("config.file", "game.properties");
    private Properties PROPERTIES = new Properties();
    private static Config config;
    private final Logger LOGGER = LogManager.getLogger(Config.class);

    public static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private Config() {
        PROPERTIES = new Properties();
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            PROPERTIES.load(reader);
        } catch (IOException e) {
            LOGGER.warn("Не получилось загрузить файл конфигурации - " + CONFIG_PATH);
        }
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return getKeyValue(key, defaultValue, Integer.class);
    }

    private <T> T getKeyValue(String key, Object defaultValue, Class<T> type) {
        Object value = PROPERTIES.get(key);
        if (value == null) {
            LOGGER.warn("Ключ " + key + " не найден в конфигурациии - " + CONFIG_PATH);
            return type.cast(defaultValue);
        }
        try {
            return type.cast(value);
        } catch (ClassCastException e) {
            //If user put string instead of int, for example
            LOGGER.error("Типы не совпадают: актуальный - " + value.getClass().getSimpleName()
                    + ", ожидаемый " + type.getSimpleName() + ". ключ - "
                    + key +", значение - " + value);
            return type.cast(defaultValue);
        }
    }
}
