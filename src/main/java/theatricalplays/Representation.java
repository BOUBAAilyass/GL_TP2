package theatricalplays;



public abstract class Representation {
    protected String name;
    protected int audience;

    public Representation(String name, int audience) {
        this.name = name;
        this.audience = audience;
    }

    public abstract double calculateAmount();

    public String getName() {
        return name;
    }

    public int getAudience() {
        return audience;
    }
}



