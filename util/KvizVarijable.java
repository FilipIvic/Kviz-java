package hr.kviz.util;

import hr.kviz.dto.PodrucjeDto;

public class KvizVarijable {
	
	public static boolean novoPodrucje = false;

	public static PodrucjeDto dajPraznoPodrucjeDto() {
		PodrucjeDto dto = new PodrucjeDto();
		dto.setIdPodrucja(-1);
		dto.setOpis("-");
		return dto;
	}

	public static PodrucjeDto[] dajPodrucjeDto() {
		PodrucjeDto dto = dajPraznoPodrucjeDto();
		PodrucjeDto[] listaPodr = { dto };
		return listaPodr;
	}

	public static boolean isNovoPodrucje() {
		return novoPodrucje;
	}

	public static void setNovoPodrucje(boolean novoPodrucje) {
		KvizVarijable.novoPodrucje = novoPodrucje;
	}
	
}
