package app.parser;

import app.map.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * ParserEngineerTest tests the following methods of ParserEngineer:
 * - getRooms() : make sure that we have the right HashMap<Integer,Room>.
 * - getStartingRoomId() : make sure that we have the correct starting id from the config file.
 * - getObjectsToCollect() : make sure that we have a HashSet of all objects inside config file.
 **/
public class ParserEngineerTest {
    private ParserEngineer parserEngineer;
    private HashMap<Integer, Room> rooms;

    @Before
    public void setUp() {
        parserEngineer = new ParserEngineer("test_files/map.xml", "test_files/config.txt");
        rooms = new HashMap<>();
        ArrayList<Integer> cardinal = new ArrayList<>();
        HashSet<String> objects = new HashSet<>();
        cardinal.add(2);
        rooms.put(1, new Room(1, "Stairway", cardinal, objects));
        objects = new HashSet<>();
        objects.add("Scarf");
        cardinal = new ArrayList<>();
        cardinal.add(1);
        rooms.put(2, new Room(2, "Hallway 1", cardinal, objects));
    }

    @Test
    public void getRooms() throws Exception {
        Room actualRoom;
        for (Map.Entry<Integer, Room> roomSet : rooms.entrySet()) {
            actualRoom = parserEngineer.getRooms().get(roomSet.getKey());
            assertNotNull(actualRoom);
            assertEquals(roomSet.getValue().getId(), actualRoom.getId());
            assertEquals(roomSet.getValue().getName(), actualRoom.getName());
            assertEquals(roomSet.getValue().getCardinal(), actualRoom.getCardinal());
            assertEquals(actualRoom.getObjects(), actualRoom.getObjects());
        }
    }

    @Test
    public void getStartingRoomId() throws Exception {
        assertEquals(1, parserEngineer.getStartingRoomId());
    }

    @Test
    public void getObjectsToCollect() throws Exception {
        HashSet<String> objectsToCollect = new HashSet<>();
        objectsToCollect.add("Scarf");
        objectsToCollect.add("Soap");
        assertEquals(objectsToCollect, parserEngineer.getObjectsToCollect());
    }

}