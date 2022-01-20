import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static java.lang.Math.*;

public class World {
    ArrayList<Airports> aeroList;
    public ArrayList<Airports> getaeroList() {
        return aeroList;
    }


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
    public Airports findByCode(String code){
        for( Airports aero : aeroList){
            if(aero.getIATA().equals(code)){
                return aero;
            }
        }
        System.out.println("Le code IATA ne correspond à aucun aeroport");
        return null;
    }

    public Airports findNearestAirport(double lon, double lat){
        double dist = distance(lon,lat,aeroList.get(0).getLongitude(),aeroList.get(0).getLatitude());
        Airports b = null;
        double index = 0;
        for(Airports a : aeroList){
            double lat1 = a.getLatitude();
            double lon1 = a.getLongitude();
            double d = distance(lon,lat,lon1, lat1);
            if(d < dist){
                b = a;
                dist = d;
            }
        }
        return b;
    }

    public double distance(double lo1, double la1, double lo2, double la2){
        double norme = pow(la2 - la1, 2) + pow((lo2 - lo1) * cos(toRadians((la2 + la1) / 2)), 2);
        return norme;
    }

    //public double distance (double lat1, double lat2, double long1, double long2) {
    }
