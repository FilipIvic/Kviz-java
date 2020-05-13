package hr.kviz.dto;

/**
 * <pre>
 * CREATE TABLE public.pitanje
(
    id_pitanja smallint NOT NULL,
    id_podrucja smallint NOT NULL,
    tekst character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pitanje_pkey PRIMARY KEY (id_pitanja),
    CONSTRAINT pitanje_fkey FOREIGN KEY (id_podrucja)
        REFERENCES public.podrucje (id_podrucja) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
 * </pre>
 * 
 * @author filip
 *
 */
//objekt pitanje//generate->hash code and equals (za uspoređivanje 2 objekta)//source->generate to String, Getter i Setter//dohvat objekta područja
public class PitanjeDto {
	private Integer idPitanja;
	private Integer idPodrucja;
	private String tekst;
	// dodatni opis područja ( baza podataka radi "join")
	private String opisPodrucja;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPitanja == null) ? 0 : idPitanja.hashCode());
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
		PitanjeDto other = (PitanjeDto) obj;
		if (idPitanja == null) {
			if (other.idPitanja != null)
				return false;
		} else if (!idPitanja.equals(other.idPitanja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PitanjeDto [idPitanja=" + idPitanja + ", idPodrucja=" + idPodrucja + ", tekst=" + tekst + "]";
	}

	public Integer getIdPitanja() {
		return idPitanja;
	}

	public void setIdPitanja(Integer idPitanja) {
		this.idPitanja = idPitanja;
	}

	public Integer getIdPodrucja() {
		return idPodrucja;
	}

	public void setIdPodrucja(Integer idPodrucja) {
		this.idPodrucja = idPodrucja;
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
	
	public PodrucjeDto getPodrucjeDto() {
		PodrucjeDto podr = new PodrucjeDto();
		podr.setIdPodrucja(idPodrucja);
		podr.setOpis(opisPodrucja);
		return podr;
	}

}
