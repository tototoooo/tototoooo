public class Airports {
    private String IATA;
    private String Name;
    private String country;
    private double latitude;
    private double longitude;

    //Constructeur

    public Airports(String IATA, String Name, String country, double latitude, double longitude){
        this.IATA = IATA;
        this.Name = Name;
        this.country = country;
        this.latitude=latitude;
        this.longitude=longitude;
    }



    public String toString(){
        return ("Airport " +this.IATA+ " " +this.Name+ " "+this.country+"" +this.longitude+ "" +this.latitude);
    }
    //Getters
    public String getIATA(){
        return this.IATA;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

}
