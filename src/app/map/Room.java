package app.map;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Room class:
 * Creates a new room object which has the following variables:
 * - id (int) : the id of the room
 * - name (String) :  the name of the room
 * - cardinal (ArrayList<Integer>) : the cardinal of the room starting from north -> east -> south -> west
 * - objects (HashSet<String>) : the objects inside the room
 *
 * The room also has two tracker variables:
 *
 * - searchedBefore (boolean) : it turns into true when the room is searched to avoid extra time and repetition of
 * objects collected!
 *
 * - cardinalTracker (int) : this tracker is used to loop through cardinals and access different room when we visit
 * the room one more time.
 *
 * *** The room has getters only because we won't change any value during runtime.***
 *
 * The room has two main methods:
 *
 * - search(HashSet<String> objectsToCollect) : it searches the room's objects looking for the objectsToCollect
 * if it finds one, it will insert it into a HashSet. After searching,
 * it will return a HashSet<String> of objects found in the room that
 * the user were looking for.
 *
 * - nextRoom() : it returns an id of the next room according to cardinalTracker and the rooms cardinals.
 * For example ( < room id="1" name="test" east="2" west="3" north="1"/> )
 * when we call nextRoom() to the object created from this xml line, it will return 1
 * because we start from north. if we try to call nextRoom() next time it will return 2
 * and so on so forth.
 */
public class Room {
    private int id;
    private String name;
    private ArrayList<Integer> cardinal;
    private HashSet<String> objects;
    private boolean searchedBefore = false;
    private int cardinalTracker = 0;

    public Room(int id, String name, ArrayList<Integer> cardinal, HashSet<String> objects) {
        this.id = id;
        this.name = name;
        this.cardinal = cardinal;
        this.objects = objects;
    }

    public HashSet<String> search(HashSet<String> objectsToCollect) {
        if (this.searchedBefore)
            return new HashSet<>();
        HashSet<String> objectsFound = new HashSet<>();
        for (String object : objectsToCollect) {
            if (objects.contains(object)) {
                objectsFound.add(object);
            }
        }
        this.searchedBefore = true;
        return objectsFound;
    }

    public int nextRoom() {
        int nextRoomId = cardinal.get(cardinalTracker % cardinal.size());
        cardinalTracker++;
        return nextRoomId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public HashSet<String> getObjects() {
        return this.objects;
    }

    public ArrayList<Integer> getCardinal() {
        return this.cardinal;
    }

    @Override
    public String toString() {
        return "Room name is " + this.name + " with id = " + this.id + "\nits cardinal is " + cardinal.toString()
                + "\nit has the following objects " + objects.toString() + "\n -------------------- \n";
    }
}
