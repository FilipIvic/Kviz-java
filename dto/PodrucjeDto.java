package hr.kviz.dto;

/**
 * <pre>
 *  
CREATE TABLE public.podrucje
(
    id_podrucja smallint NOT NULL,
    opis character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT podrucje_pkey PRIMARY KEY (id_podrucja)
)
 * </pre>
 * 
 * @author filip
 *
 */
//objekt podrucje//generate->hash code and equals (za uspoređivanje 2 objekta)//source->generate to String, Getter i Setter
public class PodrucjeDto {
	private Integer idPodrucja;
	private String opis;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPodrucja == null) ? 0 : idPodrucja.hashCode());
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
		PodrucjeDto other = (PodrucjeDto) obj;
		if (idPodrucja == null) {
			if (other.idPodrucja != null)
				return false;
		} else if (!idPodrucja.equals(other.idPodrucja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return (opis == null) ? "-" : opis.trim();
	}

	public Integer getIdPodrucja() {
		return (idPodrucja == null) ? -1 : idPodrucja;
	}

	public void setIdPodrucja(Integer idPodrucja) {
		this.idPodrucja = idPodrucja;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
}
