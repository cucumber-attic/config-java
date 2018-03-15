package io.cucumber.config.loaders;

import io.cucumber.config.Config;
import io.cucumber.config.PropertyList;
import io.cucumber.config.Value;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class ConfigLoaderContract {
    @Test
    public void configures_boolean() {
        assertEquals(true, loadConfig().get("testing.somebool").asBoolean());
    }

    @Test
    public void configures_integer() {
        assertEquals(Integer.valueOf(42), loadConfig().get("testing.meaning").asInt());
    }

    @Test
    public void configures_string_list() {
        Value listValue = loadConfig().get("testing.list");
        List<Value> list = listValue.asList();
        assertEquals("one", list.get(0).asString());
        assertEquals("two", list.get(1).asString());
    }

    private Config loadConfig() {
        Config config = new Config();
        config.set("testing.list", new PropertyList());
        ConfigLoader configLoader = makeConfigLoader();
        configLoader.load(config);
        return config;
    }

    protected abstract ConfigLoader makeConfigLoader();
}
