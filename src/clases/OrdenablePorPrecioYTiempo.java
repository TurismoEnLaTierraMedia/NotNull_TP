package clases;

import java.util.Comparator;

public class OrdenablePorPrecioYTiempo implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
		int comparacion = Integer.compare(o2.getCostoDeVisita(), o1.getCostoDeVisita());
		return comparacion;
	}

}
