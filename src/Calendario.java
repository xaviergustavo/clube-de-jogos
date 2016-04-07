import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Calendario {
	
	private Map<LocalDate, Cronograma> calendario;

	public Calendario() {
		this.calendario = new TreeMap<LocalDate, Cronograma>();
	}

	public Map<LocalDate, Cronograma> getCalendario() {
		return calendario;
	}
	
}
