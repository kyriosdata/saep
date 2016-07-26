import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class DatasSteps {
    @Step("Um mês a partir de uma dada data <table>")
    public void simplesTabela(Table table) {
        for (TableRow row : table.getTableRows()) {
            String inicio = row.getCell("Início");
            String ateInclusive = row.getCell("Fim");
            String meses = row.getCell("Meses");
            String dias = row.getCell("Dias");

            Date di = Calendario.fromDiaMesAno(inicio);
            int addMeses = Integer.parseInt(meses);

            Date resultado = Calendario.adicionaMeses(di, addMeses);

            Date df = Calendario.fromDiaMesAno(ateInclusive);
            assertEquals(df, resultado);
        }
    }
}
