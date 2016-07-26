import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Encapsula operações com datas.
 */
public class Calendario {

    public static Date adicionaMeses(Date inicio, int meses) {
        Calendar referencia = Calendar.getInstance();
        referencia.setTime(inicio);
        referencia.add(Calendar.MONTH, meses);
        return referencia.getTime();
    }

    public static Date fromDiaMesAno(String diaMesAno) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(diaMesAno);
        } catch (Exception e) {
            return new Date(0);
        }
    }

    public static String toDiaMesAno(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
