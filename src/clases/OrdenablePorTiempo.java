package clases;

import java.util.Comparator;

public class OrdenablePorTiempo implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
		return Double.compare(o1.getDuracion(), o2.getDuracion());
	}


}
