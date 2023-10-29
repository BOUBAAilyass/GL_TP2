package theatricalplays;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

  public String toHTML(Invoice invoice, Map<String, Representation> representations) {
    Calcul calcul = new Calcul();
    Customer customer = invoice.customer;
    String result = String.format("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "  <style>\n" +
            "table, th, td {\n" +
            "border: 1px solid black;\n" +
            "}\n" +
            "th, td {\n" +
            "padding: 5px;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>Invoice</h1>\n" +
            "<p><strong>Client:</strong> %s</p>\n" +
            "<table>\n" +
            "  <tr>\n" +
            "    <th>Piece</th>\n" +
            "    <th>Seats Sold</th>\n" +
            "    <th>Price</th>\n" +
            "  </tr>\n", customer.getName());
    //StringBuffer sb = new StringBuffer("Statement for %s\n");
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    StringBuffer rslt = new StringBuffer(result);
    
    for (Performance perf : invoice.performances) {

        Representation representation = representations.get(perf.playID);
        rslt.append(String.format("  <tr>\n" +
                        "    <td>%s</td>\n" +
                        "    <td>%s</td>\n" +
                        "    <td>%s</td>\n" +
                        "  </tr>\n", representation.getName(), perf.audience,
                frmt.format(calcul.calculeAmount(representation)/ 100), perf ));
    }
    

    int totalAmount = calcul.calculeTotalAmount(invoice, representations,customer);
    int volumeCredits = calcul.calculeVolumeCredits( invoice,representations);


    rslt.append(String.format("  <tr>\n" +
            "    <td colspan=\"2\" style=\"text-align: right;\"><strong>Total Owed:</strong></td>\n" +
            "    <td>%s</td>\n" +
            "  </tr>\n", frmt.format( totalAmount / 100)));
    rslt.append(String.format("  <tr>\n" +
            "    <td colspan=\"2\" style=\"text-align: right;\"><strong>Fidelity Points Earned:</strong></td>\n" +
            "    <td>%s</td>\n" +
            "  </tr>\n" 
            , frmt.format(volumeCredits)));
    rslt.append(String.format("  <tr>\n" +
            "    <td colspan=\"2\" style=\"text-align: right;\"><strong>Points left:</strong></td>\n" +
            "    <td>%s</td>\n" +
            "  </tr>\n" +
            "</table>\n" +
            "</body>\n" +
            "</html>\n", frmt.format(customer.getPoints()+volumeCredits)));
            
    return rslt.toString();
}

public String toText(Invoice invoice, Map<String, Representation> representations) {
    Calcul calcul = new Calcul();
    Customer customer = invoice.customer;
    String result = String.format("Statement for %s\n", invoice.customer.getName());
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    StringBuffer rslt = new StringBuffer(result);
    
    for (Performance perf : invoice.performances) {

        Representation representation = representations.get(perf.playID);
        rslt.append(String.format("  %s: %s (%s seats)\n", representation.getName(), frmt.format(calcul.calculeAmount(representation)/ 100), perf.audience));
    }

        int totalAmount = calcul.calculeTotalAmount(invoice, representations,customer);
        int volumeCredits = calcul.calculeVolumeCredits( invoice,representations);

    
    rslt.append(String.format("Amount owed is %s\n", frmt.format( totalAmount / 100)));
    rslt.append(String.format("You earned %s credits\n", frmt.format(volumeCredits)));
    rslt.append(String.format("Points left %s credits\n", frmt.format(invoice.customer.getPoints()+volumeCredits)));
            
    return rslt.toString();

}}
