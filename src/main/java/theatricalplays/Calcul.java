package theatricalplays;

import java.util.Map;

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

public int calculeTotalAmount(Invoice invoice,Map<String, Play> plays ) {
    int totalAmount = 0;
    for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        Calcul calcul = new Calcul();
        totalAmount += calcul.calculeAmount(play, perf);
      }
      return totalAmount;
    
}
// calculate volume credits for a performance not recursive
public int calculeVolumeCredits(Invoice invoice, Map<String, Play> plays) {
    int volumeCredits = 0;
    for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        volumeCredits += Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if (Play.PlayType.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);
      }
      return volumeCredits;

    


    
}

}
