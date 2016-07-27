import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.assertEquals;

public class DatasSteps {
    @Step("Meses a partir de uma dada data <table>")
    public void simplesTabela(Table table) {
        for (TableRow row : table.getTableRows()) {
            String inicio = row.getCell("Início");
            String ateInclusive = row.getCell("Fim");
            String meses = row.getCell("Meses");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate di = LocalDate.parse(inicio, dtf);
            int addMeses = Integer.parseInt(meses);

            LocalDate resultado = di.plusMonths(addMeses);

            LocalDate df = LocalDate.parse(ateInclusive, dtf);
            assertEquals(df, resultado);
        }
    }

    @Step("<2> meses após <01/01/2016> termina em <01/03/2016> <table>")
    public void implementation1(int meses, String inicio, String fim, Table exclusao) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate di = LocalDate.parse(inicio, dtf);
        LocalDate df = LocalDate.parse(fim, dtf);

        // Data provável sem interrupção
        LocalDate dp = di.plusMonths(meses);

        // Define total de dias não efetivos (licença, afastamento, ...)
        int totalDiasDeExclusao = 0;
        for (TableRow row : exclusao.getTableRows()) {
            LocalDate inicioExclusao = LocalDate.parse(row.getCell("InicioExclusao"), dtf);
            LocalDate fimExclusao = LocalDate.parse(row.getCell("FimExclusao"), dtf);

            long delta = fimExclusao.toEpochDay() - inicioExclusao.toEpochDay();
            totalDiasDeExclusao += delta;
        }

        LocalDate dataEsperada = dp.plusDays(totalDiasDeExclusao);

        assertEquals(df, dataEsperada);
    }
}
