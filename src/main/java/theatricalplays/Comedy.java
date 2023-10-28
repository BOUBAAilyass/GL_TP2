package theatricalplays;

public class Comedy extends Representation {
    public Comedy(String name, int audience) {
        super(name, audience);
    }

    @Override
    public double calculateAmount() {
        double amount = 30000;
        if (audience > 20) {
            amount += 10000 + 500 * (audience - 20);
        }
        amount += 300 * audience;
        return amount;
    }
}
