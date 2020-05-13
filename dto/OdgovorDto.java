package hr.kviz.dto;

/**
 * <pre>
 * CREATE TABLE public.odgovor
(
    id_pitanja smallint NOT NULL,
    redni_broj smallint NOT NULL,
    tekst character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT odgovor_pkey PRIMARY KEY (id_pitanja, redni_broj),
    CONSTRAINT odgovor_fkey FOREIGN KEY (id_pitanja)
        REFERENCES public.pitanje (id_pitanja) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
 * </pre>
 * @author filip
 *
 */

//objekt odgovori//konstuktori//source->generate to String, Getter i Setter//isTocno metoda(funkcija)

public class OdgovorDto {
	
	private Integer idPitanja;
	private Integer redniBroj;
	private String tekst;
	
	public OdgovorDto() {
		super();
	}

	public OdgovorDto(Integer idPitanja, Integer redniBroj, String tekst) {
		super();
		this.idPitanja = idPitanja;
		this.redniBroj = redniBroj;
		this.tekst = tekst;
	}

	@Override
	public String toString() {
		return "OdgovorDto [idPitanja=" + idPitanja + ", redniBroj=" + redniBroj + ", tekst=" + tekst + ", isTocno()="
				+ isTocno() + "]";
	}

	public boolean isTocno() {
		return (redniBroj == 1) ? true : false;
	}

	public Integer getIdPitanja() {
		return idPitanja;
	}

	public void setIdPitanja(Integer idPitanja) {
		this.idPitanja = idPitanja;
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

}
