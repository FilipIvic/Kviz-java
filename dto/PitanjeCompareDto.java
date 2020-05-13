package hr.kviz.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Pitanja sa odgovorima
 * 
 * @author filip
 *
 */
//isto kao i OdgovorCompare objekt, ali dohvaca i listu odgovora za svako pitanje
public class PitanjeCompareDto implements Comparable<PitanjeCompareDto> {
	private Integer idPitanja;
	private String tekst;
	private String opisPodrucja;
	private BigDecimal compareId;
	private List<OdgovorCompareDto> listaOdgovora;
	private boolean odabrano = false;

	@Override
	public int compareTo(PitanjeCompareDto o) {
		BigDecimal tmpCompareId = o.getCompareId();
		return (compareId.compareTo(tmpCompareId));
	}

	@Override
	public String toString() {
		return "PitanjeCompareDto [idPitanja=" + idPitanja + ", compareId=" + compareId + ", tekst=" + tekst
				+ ", opisPodrucja=" + opisPodrucja + ", odabrano=" + odabrano + "]";
	}

	public Integer getIdPitanja() {
		return idPitanja;
	}

	public void setIdPitanja(Integer idPitanja) {
		this.idPitanja = idPitanja;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getOpisPodrucja() {
		return opisPodrucja;
	}

	public void setOpisPodrucja(String opisPodrucja) {
		this.opisPodrucja = opisPodrucja;
	}

	public BigDecimal getCompareId() {
		return compareId;
	}

	public void setCompareId(BigDecimal compareId) {
		this.compareId = compareId;
	}

	public List<OdgovorCompareDto> getListaOdgovora() {
		return listaOdgovora;
	}

	public void setListaOdgovora(List<OdgovorCompareDto> listaOdgovora) {
		this.listaOdgovora = listaOdgovora;
	}

	public boolean isOdabrano() {
		return odabrano;
	}

	public void setOdabrano(boolean odabrano) {
		this.odabrano = odabrano;
	}

}
