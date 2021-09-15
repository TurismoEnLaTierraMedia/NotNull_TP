package clases;

import java.util.Comparator;

public class OrdenablePorPrecioYTiempo implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
		if(Integer.compare(o2.getCostoDeVisita(), o1.getCostoDeVisita()) == 0){
			return Double.compare(o2.getDuracion(), o1.getDuracion());
		}
		return Integer.compare(o2.getCostoDeVisita(), o1.getCostoDeVisita());
	}

}
