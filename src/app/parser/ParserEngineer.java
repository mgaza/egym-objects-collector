package app.parser;

import app.map.Room;

import java.util.HashSet;
import java.util.Map;

/**
 * ParserEngineer class:
 * it is a connection between the classes inside the package and outside it.
 * it takes two files ( map.xml , config.txt ) and parse them using the suitable parsers.
 *
 * ParserEngineer methods:
 * - getRooms() : return HashMap<Integer,Room> of rooms generated from mapParser.
 * HashMap[ Integer => roomID , Room => room Object ]
 *
 * - getStartingRoomId() : returns the starting room id generated from config file.
 * - getObjectsToCollect() : return a HashSet of objects we will collect generated from config file.
 */
public class ParserEngineer {
    private MapParser mapParser;
    private ConfigParser configParser;

    public ParserEngineer(String mapFile, String configFile) {
        this.mapParser = new MapParser(mapFile);
        this.configParser = new ConfigParser(configFile);
        this.mapParser.parse();
        this.configParser.parse();
    }

    public Map<Integer, Room> getRooms() {
        return this.mapParser.getRooms();
    }

    public int getStartingRoomId() {
        return this.configParser.getStartingRoomId();
    }

    public HashSet<String> getObjectsToCollect() {
        return this.configParser.getObjectsToCollect();
    }
}
