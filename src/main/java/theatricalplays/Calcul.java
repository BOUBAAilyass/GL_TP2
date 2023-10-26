package theatricalplays;

public class Calcul {

    public static final String TRAGEDY= "tragedy";
    public static final String COMEDY = "comedy";

  public double  calculeAmount(Play play,Performance perf){
        double thisAmount = 0;
        switch (play.type) {
            // calculate amount for a tragedy
            case TRAGEDY:
              thisAmount = 40000;
              if (perf.audience > 30) {
                thisAmount += 1000 * (perf.audience - 30);
              }
              break;
            // calculate amount for a comedy
            case COMEDY:
              thisAmount = 30000;
              if (perf.audience > 20) {
                thisAmount += 10000 + 500 * (perf.audience - 20);
              }
              thisAmount += 300 * perf.audience;
              break;
            // throw error for unknown type
            default:
              throw new Error("unknown type: ${play.type}");
          }
          return thisAmount;
    }

    
}
