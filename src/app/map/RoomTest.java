package app.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * RoomTest:
 * - Test search method of the room!
 * - Test nextRoom method of the room!
 * - Test getters methods!
 */
public class RoomTest {
    private Room room;

    @Before
    public void setUp() {
        ArrayList<Integer> cardinal = new ArrayList<>();
        cardinal.add(3);
        cardinal.add(4);
        HashSet<String> objects = new HashSet<>();
        objects.add("Laptop");
        objects.add("Mobile");
        room = new Room(100, "Mohammed", cardinal, objects);
    }

    @Test
    public void search() throws Exception {
        HashSet<String> objectsToCollect = new HashSet<>();
        HashSet<String> objectsFound = new HashSet<>();
        objectsToCollect.add("Laptop");
        objectsFound.add("Laptop");
        assertEquals(objectsFound, room.search(objectsToCollect));
        objectsToCollect.add("Headphones");
        assertEquals(0, room.search(objectsToCollect).size()); // because it has been search before !
        assertEquals(2, objectsToCollect.size());

    }

    @Test
    public void nextRoom() throws Exception {
        assertEquals(3, room.nextRoom());
        assertEquals(4, room.nextRoom());
    }

    @Test
    public void getId() throws Exception {
        assertEquals(100, room.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Mohammed", room.getName());
    }

    @Test
    public void getObjects() throws Exception {
        HashSet<String> objectsExpeced = new HashSet<>();
        objectsExpeced.add("Laptop");
        objectsExpeced.add("Mobile");
        assertEquals(objectsExpeced, room.getObjects());
    }

    @Test
    public void getCardinal() throws Exception {
        ArrayList<Integer> cardinalsExpected = new ArrayList<>();
        cardinalsExpected.add(3);
        cardinalsExpected.add(4);
        assertEquals(cardinalsExpected, room.getCardinal());
    }

}