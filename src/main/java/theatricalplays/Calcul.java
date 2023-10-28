package theatricalplays;

import java.util.Map;

public class Calcul {


    public double calculeAmount(Representation representation) {
      return representation.calculateAmount();
  }

  public int calculeTotalAmount(Invoice invoice, Map<String, Representation> representations) {
    int totalAmount = 0;
    for (Performance perf : invoice.performances) {
        Representation representation = representations.get(perf.playID);
        totalAmount += calculeAmount(representation);
    }
    return totalAmount;
}

// calculate volume credits for a performance not recursive
public int calculeVolumeCredits(Invoice invoice, Map<String, Representation> representations) {
  int volumeCredits = 0;
  for (Performance perf : invoice.performances) {
      Representation representation = representations.get(perf.playID);
      volumeCredits += Math.max(perf.audience - 30, 0);
      if (representation instanceof Comedy) {
          volumeCredits += Math.floor(perf.audience / 5);
      }
  }
  return volumeCredits;
}

}
