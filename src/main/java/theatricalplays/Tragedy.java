package theatricalplays;

public class Tragedy extends Representation {
    public Tragedy(String name, int audience) {
        super(name, audience);
    }

    @Override
    public double calculateAmount() {
        double amount = 40000;
        if (audience > 30) {
            amount += 1000 * (audience - 30);
        }
        return amount;
    }
}
