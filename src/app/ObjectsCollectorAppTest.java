package app;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * ObjectsCollectorAppTest tests the run method and make sure that we will get the intended results !
 */
public class ObjectsCollectorAppTest {

    @Test
    public void run() throws Exception {
        ObjectsCollectorApp.run("test_files/map.xml", "test_files/config.txt", "test_files/output.xml");
        Scanner actualFile = new Scanner(new File("test_files/output.xml"));
        Scanner expectedFile = new Scanner(new File("test_files/expectedOutput.xml"));
        while (actualFile.hasNextLine())
            assertEquals(actualFile.nextLine(), expectedFile.nextLine());
    }

}