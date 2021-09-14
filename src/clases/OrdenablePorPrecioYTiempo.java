package clases;

import java.util.Comparator;

public class OrdenablePorPrecioYTiempo implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
		int comparacion = Integer.compare(o2.getCostoDeVisita(), o1.getCostoDeVisita());
//		if(comparacion == 0) {
//			comparacion = Double.compare(o2.getDuracion(), o1.getDuracion());
//		}
		return comparacion;
	}


}
