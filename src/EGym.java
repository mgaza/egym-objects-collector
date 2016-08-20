import app.ObjectsCollectorApp;

/**
 * EGym contains the main method to test ObjectsCollectorApp
 */
public class EGym
{
    public static void main(String args[])
    {
        try
        {
            ObjectsCollectorApp.run(args[0],args[1],args[2]);
        }
        catch (NullPointerException exception)
        {
            System.err.println("wrong args!");
            System.out.println("Usage: java EGym map.xml config.txt output.xml");
        }

    }
}
