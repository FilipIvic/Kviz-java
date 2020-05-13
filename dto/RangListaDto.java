package hr.kviz.dto;

import java.util.List;

/**
 * Vraća trenutnu poziciju nakon kraja odgovaranja i rang listu.
 * @author filip
 *
 */
//samo jedan objekt sa više fieldova (+ lista)
public class RangListaDto {
	private int pozicija;
	private String info;
	private List<RezultatDto> rangLista;

	@Override
	public String toString() {
		return "RangListaDto [pozicija=" + pozicija + ", info=" + info + "]";
	}

	public int getPozicija() {
		return pozicija;
	}

	public void setPozicija(int pozicija) {
		this.pozicija = pozicija;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<RezultatDto> getRangLista() {
		return rangLista;
	}

	public void setRangLista(List<RezultatDto> rangLista) {
		this.rangLista = rangLista;
	}

}
