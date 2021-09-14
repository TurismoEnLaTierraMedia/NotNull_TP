package clases;

import java.util.Comparator;

public class OrdenablePorPrecio implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
		return Integer.compare(o1.getCostoDeVisita(), o2.getCostoDeVisita());
	}


}
