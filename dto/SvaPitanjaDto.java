package hr.kviz.dto;

import java.util.ArrayList;
import java.util.List;

import hr.kviz.util.KrajException;

//iz svih pitanja uzimamo grupu pitanja za prikaz na ekranu i pamtimo izabrana podrucja ( da se ne duplaju ) te odabrana pitanja da se ne ponavljaju
public class SvaPitanjaDto {
	private List<PitanjeCompareDto> svaPitanja;
	private List<PitanjeCompareDto> grupaPitanja;
	private int pitanjeBroj = 0;
	private int index = 0;
	private int ukupnoPitanja;

	@Override
	public String toString() {
		return "SvaPitanjaDto [index=" + index + "]";
	}

	/**
	 * Dohvaća se sljedeći set pitanja za prikaz.
	 * Odabiremo različita područja i pitanja koja već nisu odabrana.
	 * @return
	 * @throws KrajException 
	 */
	public List<PitanjeCompareDto> sljedecaGrupaPitanja() throws KrajException {
		pitanjeBroj++;
		grupaPitanja = new ArrayList<PitanjeCompareDto>();
		List<String> listaPodrucja = new ArrayList<String>();
		int odPocetka = 0;
		int broj = 0;
		
		System.out.println("----------- start odabira --------------");
		while (broj < 4) {
			if (index >= ukupnoPitanja) {
				index = 0; //uzmi pitanja od početka
				odPocetka++;
				if (odPocetka == 2) {
					throw new KrajException("Nema više slobodnih pitanja za izbor!");
				}
			}
			PitanjeCompareDto pitanje = svaPitanja.get(index++);
			System.out.println(pitanje);				
			if (pitanje.isOdabrano() == false) { //ovo pitanje nije odabrano				
				String podrucje = pitanje.getOpisPodrucja();
				if (listaPodrucja.contains(podrucje) == false) { //na pregled nudimo samo različita područja					
					listaPodrucja.add(podrucje);
					grupaPitanja.add(pitanje);
					broj++;
				}
			}
		}
		System.out.println("----------- kraj odabira --------------");
		return grupaPitanja;
	}
	

	public int getPitanjeBroj() {
		return pitanjeBroj;
	}

	public void setSvaPitanja(List<PitanjeCompareDto> svaPitanja) {
		this.svaPitanja = svaPitanja;
		ukupnoPitanja = svaPitanja.size();
	}
		
}
