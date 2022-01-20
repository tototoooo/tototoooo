public class Main {

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            Airports aero1 = new Airports("DSU","NOT", "FR", 265,340);
            Airports aero2 = new Airports("DSA","OUT", "ESPN", 255,300);



            System.out.println(aero1.toString());
            System.out.println(aero2.toString());
            //System.out.println("Nombre d'aréoports : " +aero1.getNumber());
        //etu2 = null;
            World w = new World ("./data/airport-codes_no_comma.csv");
            System.out.println("Found "+w.getaeroList().size()+" airports.");
            Airports paris = w.findNearestAirport(2.316,48.866);
            Airports cdg = w.findByCode("CDG");
            double distance = w.distance(2.316,48.866,paris.getLongitude(),paris.getLatitude());
            System.out.println(paris);
            System.out.println(distance);
            double distanceCDG = w.distance(2.316,48.866,cdg.getLongitude(),cdg.getLatitude());
            System.out.println(cdg);
            System.out.println(distanceCDG);
        }
}