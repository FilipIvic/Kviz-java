package hr.kviz.dto;

import java.math.BigDecimal;

/**
 * @author filip
 *
 */
//isti objekt ( ali implementira uspoređivanje objekata ) kao odgovorDto, sluzi kasnije za sortiranje pitanja//konstruktor te default funkciju compareTo//
public class OdgovorCompareDto implements Comparable<OdgovorCompareDto> {

	private BigDecimal compareId;
	private Integer redniBroj;
	private String tekst;
	

	public OdgovorCompareDto() {
		super();
	}

	@Override
	public int compareTo(OdgovorCompareDto o) {
		BigDecimal tmpCompareId = o.getCompareId();
		return (compareId.compareTo(tmpCompareId));
	}

	@Override
	public String toString() {
		return "OdgovorCompareDto [tekst=" + tekst + ", compareId=" + compareId
				+ ", isTocno()=" + isTocno() + "]";
	}

	public boolean isTocno() {
		return (redniBroj == 1) ? true : false;
	}

	public Integer getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public BigDecimal getCompareId() {
		return compareId;
	}

	public void setCompareId(BigDecimal compareId) {
		this.compareId = compareId;
	}

}
