package app.parser;

import app.map.Room;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * MapParser class creates a new object of MapParser
 * it has the following variables:
 * - mapXmlFile (String) : it stores the name of the map file ( E.g Map.xml )
 * - rooms ( Map<Integer,Room> ) : it stores objects of every room in the map file.
 *
 * MapParser has the following methods:
 * - parse() : which parse map.xml and get back a HashMap of all rooms inside the file.
 *
 * MapParser hash all getters and a setter of only the mapXmlFile.
 *
 * There are private methods of MapParser to help parser do its job ( They have there own comments ;) )
 */
public class MapParser implements Parser {
    private String mapXMLFile;
    private Map<Integer, Room> rooms;

    protected MapParser(String mapXMLFile) {
        this.mapXMLFile = mapXMLFile;
        this.rooms = new HashMap<>();
    }

    public void parse() {
        try {
            Document map = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(this.mapXMLFile));
            NodeList roomsNodes = map.getElementsByTagName("room");
            for (int i = 0, n = roomsNodes.getLength(); i < n; i++) {
                Room room = this.getRoomFromXMLFile(roomsNodes.item(i).getAttributes(), roomsNodes.item(i).getChildNodes());
                this.rooms.put(room.getId(), room);
            }
        } catch (Exception e) {
            System.err.println("There was an error while parsing [" + this.mapXMLFile + "] !");
            e.printStackTrace();
        }
    }

    public void setMapXMLFile(String mapXMLFile) {
        this.mapXMLFile = mapXMLFile;
    }

    public String getMapXMLFile() {
        return this.mapXMLFile;
    }

    public Map<Integer, Room> getRooms() {
        return this.rooms;
    }

    /*
        getRoomFromXMLFile method:
            - takes attributes and objectNodes of a single room node as an input
            - make a new room from the values of attributes and objects
     */
    private Room getRoomFromXMLFile(NamedNodeMap attributes, NodeList objectNodes) {
        int roomID = Integer.parseInt(attributes.getNamedItem("id").getNodeValue());
        String roomName = attributes.getNamedItem("name").getNodeValue();
        ArrayList<Integer> roomCardinal = this.getCardinalFromXMLFILE(attributes);
        HashSet<String> roomObjects = this.getObjectsFromXMLFile(objectNodes);

        return new Room(roomID, roomName, roomCardinal, roomObjects);
    }

    /*
        getCardinalFromXMLFILE method:
            - takes attributes of a room and check the cardinal of this room
            - return an array list which contains the ids of rooms that you can go through.
     */
    private ArrayList<Integer> getCardinalFromXMLFILE(NamedNodeMap attributes) {
        ArrayList<Integer> cardinal = new ArrayList<>();
        String directions[] = {"north", "east", "south", "west"}; // change the order the output will change!

        for (int i = 0; i < 4; i++) {
            try {
                cardinal.add(Integer.parseInt(attributes.getNamedItem(directions[i]).getNodeValue()));
            } catch (NullPointerException er) {
                // we will do nothing if the direction isn't an attribute of the node
            }
        }

        return cardinal;
    }

    /*
        getObjectsFromXMLFile method
            - takes objectNodes of a room as an input
            - check for name attribute for every object
            - put the object name inside a HashSet
            - return HashSet
     */
    private HashSet<String> getObjectsFromXMLFile(NodeList objectNodes) {
        HashSet<String> objects = new HashSet<>();
        for (int i = 0, n = objectNodes.getLength(); i < n; i++) {
            if (objectNodes.item(i).getNodeType() == Node.ELEMENT_NODE)
                objects.add(objectNodes.item(i).getAttributes().getNamedItem("name").getNodeValue());
        }
        return objects;
    }
}
