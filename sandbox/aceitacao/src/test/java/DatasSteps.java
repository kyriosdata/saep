import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

public class DatasSteps {
    @Step("Quantidade de dias por mês <table>")
    public void simplesTabela(Table table) {
        for (TableRow row : table.getTableRows()) {
            String inicio = row.getCell("Início");
            String ateInclusive = row.getCell("Até (inclusive)");
            System.out.println(ateInclusive);
        }
    }
}
