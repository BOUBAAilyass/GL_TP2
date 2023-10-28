package theatricalplays;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml;

public class StatementPrinterTests {

    @Test
    void exampleStatementText() {
        Map<String, Representation> representations = Map.of(
        "hamlet",  new Tragedy("Hamlet", 55),
        "as-like", new Comedy("As You Like It", 35),
        "othello", new Tragedy("Othello", 40));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.toText(invoice, representations);
        verify(result);
    }
    
    @Test
    void exampleStatementHTML() {
        Map<String, Representation> representations = Map.of(
        "hamlet",  new Tragedy("Hamlet", 55),
        "as-like", new Comedy("As You Like It", 35),
        "othello", new Tragedy("Othello", 40));
        
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.toHTML(invoice, representations);
        

        verifyHtml(result);
    }
    
}
