package theatricalplays;

public class Customer {
    public String name;
    public int numClient;
    public int points; 

    public Customer(String name, int numClient, int points) {
        this.name = name;
        this.numClient = numClient;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getNumClient() {
        return numClient;
    }

    public int getPoints() {
        return points;
    }


    public void setPoints(int points) {
        this.points = points;
    }



}
