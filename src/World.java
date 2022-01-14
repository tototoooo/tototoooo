import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class World {
    ArrayList<Airports> aeroList;


    public World(String fileName) {
        ArrayList<Airports> listAirports = new ArrayList<Airports>();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while (s != null) {
                s = s.replaceAll("\"", "");
//Enleve les guillemets qui s´eparent les champs de donn´ees GPS.
                String fields[] = s.split(",");
// Une bonne id´ee : placer un point d'arr^et ici pour du debuggage.
                if (fields[1].equals("large_airport")) {

                    Airports aero1= new Airports( fields[9], fields[2], fields[5], Double.parseDouble(fields[11]),Double.parseDouble(fields[12]));
                }
                s = buf.readLine();
            }
        }

        catch(Exception e){
                System.out.println("Maybe the file isn't there ?");
                System.out.println(aeroList.get(aeroList.size() - 1));
                e.printStackTrace();
            }

        }
    //public double distance (double lat1, double lat2, double long1, double long2) {
    }
