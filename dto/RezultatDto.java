package hr.kviz.dto;

/**
 * <pre>
 * CREATE TABLE public.rezultat 
 * ( id_rezultata integer NOT NULL, 
 *   id_natjecatelja smallint NOT NULL, 
 *   vrijeme timestamp without time zone NOT NULL, 
 *   broj_pitanja smallint NOT NULL, 
 *   broj_tocnih smallint NOT NULL, 
 *   bodova smallint NOT NULL,
 * <pre>
 * 
 * @author filip
 *
 */
//objekt rezultat//generate->hash code and equals (za uspoređivanje 2 objekta)//source->generate to String, Getter i Setter//
public class RezultatDto {
	private Integer poredak;
	private Integer idRezultata;
	private Integer idNatjecatelja;
	private String vrijeme;
	private Integer brojPitanja;
	private Integer brojTocnih;
	private Integer bodova;
	//join na tabelu natjecatelja
	private String ime;

	public void addTocno() {
		if (brojTocnih == null) {
			brojTocnih = new Integer(0);
		}
		int broj = brojTocnih.intValue() + 1;
		brojTocnih = broj;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRezultata == null) ? 0 : idRezultata.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RezultatDto other = (RezultatDto) obj;
		if (idRezultata == null) {
			if (other.idRezultata != null)
				return false;
		} else if (!idRezultata.equals(other.idRezultata))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RezultatDto [poredak=" + poredak + ", idRezultata=" + idRezultata + ", idNatjecatelja=" + idNatjecatelja
				+ ", vrijeme=" + vrijeme + ", brojPitanja=" + brojPitanja + ", brojTocnih=" + brojTocnih + ", bodova="
				+ bodova + ", ime=" + ime + "]";
	}

	public Integer getPoredak() {
		return poredak;
	}

	public void setPoredak(Integer poredak) {
		this.poredak = poredak;
	}

	public Integer getIdRezultata() {
		return idRezultata;
	}

	public void setIdRezultata(Integer idRezultata) {
		this.idRezultata = idRezultata;
	}

	public Integer getIdNatjecatelja() {
		return idNatjecatelja;
	}

	public void setIdNatjecatelja(Integer idNatjecatelja) {
		this.idNatjecatelja = idNatjecatelja;
	}

	public String getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(String vrijeme) {
		this.vrijeme = vrijeme;
	}

	public Integer getBrojPitanja() {
		return brojPitanja;
	}

	public void setBrojPitanja(Integer brojPitanja) {
		this.brojPitanja = brojPitanja;
	}

	public Integer getBrojTocnih() {
		return brojTocnih;
	}

	public void setBrojTocnih(Integer brojTocnih) {
		this.brojTocnih = brojTocnih;
	}

	public Integer getBodova() {
		return bodova;
	}

	public void setBodova(Integer bodova) {
		this.bodova = bodova;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

}
