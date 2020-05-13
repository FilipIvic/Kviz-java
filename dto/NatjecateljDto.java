package hr.kviz.dto;

/**
 * <pre>
 * CREATE TABLE public.natjecatelj
(
    id_natjecatelja smallint NOT NULL,
    id_prijave character(25) COLLATE pg_catalog."default" NOT NULL,
    lozinka character(25) COLLATE pg_catalog."default" NOT NULL,
    ime character(15) COLLATE pg_catalog."default",
    uloga character(1) COLLATE pg_catalog."default",
    registracija date,
    datum_prijave date,
    CONSTRAINT natjecatelj_pkey PRIMARY KEY (id_natjecatelja)
)
 * </pre>
 * 
 * @author filip
 *
 */
//objekt natjecatelj//source->generate to String, Getter i Setter

public class NatjecateljDto {
	private Integer idNatjecatelja;
	private String idPrijave;
	private String lozinka;
	private String ime;
	private String uloga;
	private String registracija;
	private String datumPrijave;

	@Override
	public String toString() {
		return "NatjecateljDto [idNatjecatelja=" + idNatjecatelja + ", idPrijave=" + idPrijave + ", lozinka=" + lozinka
				+ ", ime=" + ime + ", uloga=" + uloga + ", registracija=" + registracija + ", datumPrijave="
				+ datumPrijave + "]";
	}

	public Integer getIdNatjecatelja() {
		return idNatjecatelja;
	}

	public void setIdNatjecatelja(Integer idNatjecatelja) {
		this.idNatjecatelja = idNatjecatelja;
	}

	public String getIdPrijave() {
		return idPrijave;
	}

	public void setIdPrijave(String idPrijave) {
		this.idPrijave = idPrijave;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(String datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	
}
