package theatricalplays;

import java.util.Map;

public class Calcul {

  public Invoice invoice;
  public Map<String, Representation> representations;
  public Customer customer;

Calcul(Invoice invoice, Map<String, Representation> representations, Customer customer){
  this.invoice = invoice;
  this.representations = representations;
  this.customer = customer;
}


    public double calculeAmount(Representation representation) {
      return representation.calculateAmount();
  }

  public int calculeTotalAmount() {
    int totalAmount = 0;
    for (Performance perf : invoice.performances) {
        Representation representation = representations.get(perf.playID);
        totalAmount += calculeAmount(representation);

    }
    int customerPoints = customer.getPoints();


    if (customerPoints >150){
    customer.setPoints(customerPoints - 150);
    totalAmount -=  1500;  
  }
    return totalAmount;
}

// calculate volume credits for a performance not recursive
public int calculeVolumeCredits() {
  int volumeCredits = 0;
  for (Performance perf : invoice.performances) {
      Representation representation = representations.get(perf.playID);
      volumeCredits += Math.max(perf.audience - 30, 0);
      if (representation instanceof Comedy) {
          volumeCredits += Math.floor(perf.audience / 5);
      }
  }

  customer.setPoints(customer.getPoints()+volumeCredits);
  return volumeCredits;
}

}
