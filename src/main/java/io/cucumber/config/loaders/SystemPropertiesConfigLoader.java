package io.cucumber.config.loaders;

import io.cucumber.config.Config;
import io.cucumber.config.Property;

import java.util.Map;

public class SystemPropertiesConfigLoader implements ConfigLoader {
    private final Map<Object, Object> properties;

    public SystemPropertiesConfigLoader() {
        this(System.getProperties());
    }

    public SystemPropertiesConfigLoader(Map<Object, Object> properties) {
        this.properties = properties;
    }

    @Override
    public void load(Config config) {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key instanceof String && value instanceof String) {
                String stringValue = (String) value;
                for (String v : stringValue.split(",")) {
                    String stringKey = (String) key;
                    config.set(stringKey, new Property(v, stringKey + " JVM system property"));
                }
            }
        }
    }
}
