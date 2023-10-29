package theatricalplays;



public abstract class Representation {
    protected String name;
    protected int audience;
    protected Customer customer;

    public Representation(String name, int audience, Customer customer) {
        this.name = name;
        this.audience = audience;
        this.customer = customer;
    }

    public abstract double calculateAmount();

    public String getName() {
        return name;
    }

    public int getAudience() {
        return audience;
    }

    public Customer getCustomer() {
        return customer;
    }

}



