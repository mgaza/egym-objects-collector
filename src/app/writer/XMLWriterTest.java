package app.writer;

import app.map.Room;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * XMLWriterTest tests the following methods:
 * - saveOutputFile() : make sure that we save the file correctly depending on the document object.
 */
public class XMLWriterTest {
    private XMLWriter xmlWriter;

    @Before
    public void setUp() {
        xmlWriter = new XMLWriter("test_files/xmlWriter/ActualOutput.xml");
        Element route = xmlWriter.createRouteElement();
        Element mohammedRoom = xmlWriter.createRoomElement(new Room(1, "Mohammed", new ArrayList<Integer>(),
                new HashSet<String>()));
        Element anotherRoom = xmlWriter.createRoomElement(new Room(2, "Another", new ArrayList<Integer>(),
                new HashSet<String>()));
        Element mohammedRoomObject = xmlWriter.createObjectElement("Laptop");
        mohammedRoom.appendChild(mohammedRoomObject);
        route.appendChild(mohammedRoom);
        route.appendChild(anotherRoom);
        xmlWriter.saveOutputFile();
    }

    @Test
    public void saveOutputFile() throws Exception {
        Scanner actualFile = new Scanner(new File("test_files/xmlWriter/ActualOutput.xml"));
        Scanner expectedFile = new Scanner(new File("test_files/xmlWriter/ExpectedOutput.xml"));
        while (actualFile.hasNextLine())
            assertEquals(actualFile.nextLine(), expectedFile.nextLine());
    }

}