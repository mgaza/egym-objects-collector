package app.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * ConfigParser class create an object of ConfigParser
 * it contains the following variables:
 * - configFileName (String) : store the name of the config file ( E.G: config.txt )
 * - startingRoomId (int) : store the id of the starting room after parsing config file.
 * - objectsToCollect ( HashSet<String> ) : store the objects that will be collected. ( from config file )
 *
 * ConfigParser methods:
 * - parse() : it will parse config file and set the values of startingRoomId and objectsToCollect variables.
 *
 * ConfigParser has all the getters methods and a setter method of only the configFileName. ( For Future consideration)
 */
public class ConfigParser implements Parser {
    private String configFileName;
    private int startingRoomId;
    private HashSet<String> objectsToCollect;

    protected ConfigParser(String configFileName) {
        this.configFileName = configFileName;
        this.startingRoomId = -1;
        this.objectsToCollect = new HashSet<>();
    }

    public void parse() {
        try {
            Scanner fileScanner = new Scanner(new File(this.configFileName));
            this.startingRoomId = Integer.parseInt(fileScanner.nextLine());
            while ((fileScanner.hasNextLine()))
                this.objectsToCollect.add(fileScanner.nextLine());
        } catch (FileNotFoundException exception) {
            System.err.print(this.configFileName + " can't be found!\n");
            exception.printStackTrace();
        } catch (Exception otherExceptions) {
            System.err.print("Please make sure that the file is a valid config file!");
            otherExceptions.printStackTrace();
        }
    }

    public String getConfigFileName() {
        return this.configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public int getStartingRoomId() {
        return this.startingRoomId;
    }

    public HashSet<String> getObjectsToCollect() {
        return this.objectsToCollect;
    }
}
