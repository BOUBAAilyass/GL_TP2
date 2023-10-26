package theatricalplays;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

  public String print(Invoice invoice, Map<String, Play> plays) {
    
    double totalAmount = 0;
    double volumeCredits = 0;
    
    StringWriter writer = new StringWriter();
    PrintWriter printer = new PrintWriter(writer);
    printer.println(String.format("Statement for %s", invoice.customer));
    
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
    // calculate amount 
      
      Calcul calcul = new Calcul();
      double thisAmount = calcul.calculeAmount(play, perf);
      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (Play.PlayType.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      printer.println(String.format("  %s: %s (%s seats)", play.name, frmt.format(thisAmount / 100), perf.audience));
      totalAmount += thisAmount;
    }
    printer.println(String.format("Amount owed is %s", frmt.format(totalAmount / 100)));
    printer.println(String.format("You earned %s credits", volumeCredits));
    String result = writer.toString();
     return result;
  }

}
