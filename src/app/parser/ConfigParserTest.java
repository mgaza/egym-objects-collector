package app.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * ConfigParserTest test the following methods:
 * - getStartingRoomId() : check that we get the right value from config file.
 * - getObjectsToCollect() : check that we get all objects from config file.
 */
public class ConfigParserTest {
    private ConfigParser configParser;

    @Before
    public void setUp() {
        configParser = new ConfigParser("test_files/config.txt");
        configParser.parse();
    }

    @Test
    public void getStartingRoomId() throws Exception {
        assertEquals(1, configParser.getStartingRoomId());
    }

    @Test
    public void getObjectsToCollect() throws Exception {
        HashSet<String> objectsToCollect = new HashSet<>();
        objectsToCollect.add("Scarf");
        objectsToCollect.add("Soap");
        assertEquals(objectsToCollect, configParser.getObjectsToCollect());
    }

}