package app;

import app.map.Room;
import app.parser.ParserEngineer;
import app.writer.XMLWriter;
import org.w3c.dom.Element;

import java.util.HashSet;

/**
 * ObjectsCollectorApp class:
 * this class is responsible for running the app and searching for objects depending on config file.
 *
 * it contains the following methods:
 * - run(String mapFile,String configFile, String outputFile) :
 * creates a new ParserEngineer and an XMLWriter
 * trigger the private method search to search for objects.
 *
 * there are three private methods:
 * - search() : it searches for objects and write to XMLWriter. ( For better performance )
 * - getStartingRoomId() : checks if the startingRoomId is valid.
 * - getNextRoom() : checks if the nextRoom if a valid room.
 */
public class ObjectsCollectorApp {
    public static void run(String mapFile, String configFile, String outputFile) {
        ParserEngineer parserEngineer = new ParserEngineer(mapFile, configFile);
        XMLWriter output = new XMLWriter(outputFile);
        search(parserEngineer, output);

    }

    private static void search(ParserEngineer parserEngineer, XMLWriter output) {
        Element routeElement, roomElement, objectElement;
        routeElement = output.createRouteElement();
        Room currentRoom = parserEngineer.getRooms().get(getStartingRoomId(parserEngineer));
        HashSet<String> objectsFound;

        while (parserEngineer.getObjectsToCollect().size() != 0) {
            objectsFound = currentRoom.search(parserEngineer.getObjectsToCollect());
            parserEngineer.getObjectsToCollect().removeAll(objectsFound);
            roomElement = output.createRoomElement(currentRoom);

            if (objectsFound.size() > 0) {
                for (String object : objectsFound) {
                    objectElement = output.createObjectElement(object);
                    roomElement.appendChild(objectElement);
                }
            }
            routeElement.appendChild(roomElement);
            currentRoom = getNextRoom(parserEngineer, currentRoom);
        }
        output.saveOutputFile();
    }

    private static int getStartingRoomId(ParserEngineer parserEngineer) {
        if (parserEngineer.getRooms().containsKey(parserEngineer.getStartingRoomId()))
            return parserEngineer.getStartingRoomId();
        System.err.println("Starting Room Id is wrong! Please check your config file!");
        System.exit(0);

        return -1;
    }

    private static Room getNextRoom(ParserEngineer parserEngineer, Room currentRoom) {
        int nextRoom = currentRoom.nextRoom();
        if (parserEngineer.getRooms().containsKey(nextRoom))
            return parserEngineer.getRooms().get(nextRoom);

        System.err.println("Room id is out of range! Please check your Map File!");
        System.exit(-1);

        return null;
    }

}
