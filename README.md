# egym-objects-collector
Create an output.xml file and write a route into this file, that collects all the objects specified in the text file

There are three files. One XML file describes an adventure game map (map.xml) which includes “rooms" and “objects". A room may or may not permit travel in one of the four cardinal directions and may or may not contain "objects". The second and third file (config1.txt and config2.txt) are plain text files where the first line indicates the ID of the room the player starts in and each subsequent line lists the name of an object they must collect. The challenge you need to solve is to write a program that will:

- Accept 3 command line parameters. Example: java YourProgram map.xmlconfig.txt output.xml
- Parse the map.xmland create a model of the game map.
- Read the config.txt file to know where to start and which objects to collect.
- Create an output.xmlfile and write a route into this file, that collects all the objects specified in the text file.

Given the above example, the following is one of the potentially correct output.xml for config1.txt (1 Scarf Soap)
<route>    <room id="1" name="Stairway"/>       <room id="2" name="Hallway 1">        <object name="Scarf"/>    </room>    <room id="4" name="Storage Room"/>    <room id="2" name="Hallway 1"/>    <room id="3" name="Hallway 2"/>    <room id="5" name="Kitchen"/>    <room id="8" name="Bed Room"/>    <room id="7" name="Living Room"/>    <room id="8" name="Bed Room"/>    <room id="5" name="Kitchen"/>    <room id="3" name="Hallway 2"/>    <room id="6" name="Bath Room">        <object 
name="Soap"/>    </room></route>

#Logical constraints:
- The order in which the objects are collected does not matter.
- The route should be determined without any further user input.
- The routing and subsequent text output should be stopped as soon as all items have been collected.
- The ids of the directions match the room ids.
