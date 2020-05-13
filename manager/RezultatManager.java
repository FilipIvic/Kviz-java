package hr.kviz.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import hr.kviz.dto.RangListaDto;
import hr.kviz.dto.RezultatDto;
import hr.kviz.gui.RezultatPanel;
import hr.kviz.sql.KvizSqlUtil;
import hr.kviz.sql.PostgreConn;
import hr.kviz.sql.RezultatSql;

public class RezultatManager {

	private Connection conn;
	private RezultatSql rezsql = new RezultatSql(conn);

	/**
	 * Unos rezultata, traženje poretka i prikaz svih.
	 * @param panelLista
	 * @param rezultatdto
	 */
	public void prikazRezultata(JPanel panelLista, RezultatDto rezultatdto) {
		
		panelLista.removeAll();
		
		//korekcija broja pitanja radi isteka vremena
		int brojpitanja = rezultatdto.getBrojPitanja().intValue() - 1;
		int brojTocnih = rezultatdto.getBrojTocnih().intValue();
		if (brojTocnih > brojpitanja) {
			brojpitanja = brojTocnih;
		}
		rezultatdto.setBrojPitanja(brojpitanja);
		
		List<RezultatDto> listaRezultata = null;
		try {
			conn = PostgreConn.dajKonekciju();
			rezsql = new RezultatSql(conn);
			
			//1. unos rezultata
			rezultatdto.setIdRezultata(rezsql.dajMaxIdRezultata());
			rezultatdto.setVrijeme(rezsql.dajTimestamp());
			rezsql.unosRezultata(rezultatdto);

			//dohvat top 10 i provjera rezultata je li u listi
			listaRezultata = rezsql.dajListuTop10();
			if (listaRezultata.contains(rezultatdto) == false) {
				//mi nismo tu, nađi trenutni poredak i stavi nas na zadnju poziciju
				int poredak = rezsql.dajPoredak(rezultatdto.getBodova());
				rezultatdto.setPoredak(poredak);
				listaRezultata.remove(listaRezultata.size() - 1);
				listaRezultata.add(rezultatdto);
			}
			
		} catch (Exception e) {
			listaRezultata = new ArrayList<RezultatDto>();
		} finally {
			KvizSqlUtil.close(conn);
		}
		
		/*RezultatTableModel tm = new RezultatTableModel(listaRezultata);
		JTable table = new JTable(tm);
		KvizUtil.setColumnWidths(table, 50, 150, 100, 50, 50, 50);
		JScrollPane scrpane = new JScrollPane(table);
		panelLista.add(scrpane, BorderLayout.CENTER);*/
		
		//prikaz rezultata na panel
		RezultatPanel rezultati = new RezultatPanel();
		int red = 1;
		for (RezultatDto dto : listaRezultata) {
			//ovo boja istog natjecatelja
			//boolean opaque = dto.getIdNatjecatelja().intValue() == rezultatdto.getIdNatjecatelja().intValue(); 
			//ovo boja zadnji rezultat
			boolean opaque = dto.getIdRezultata().intValue() == rezultatdto.getIdRezultata().intValue(); 
			Color bojaOkvira = (opaque) ? Color.GREEN : Color.YELLOW;
			List<String> podaci = new ArrayList<>();
			podaci.add(dto.getPoredak().toString());
			podaci.add(dto.getIme() + "  ");
			podaci.add(dto.getVrijeme() + "  ");
			podaci.add(dto.getBrojPitanja().toString());
			podaci.add(dto.getBrojTocnih().toString());
			podaci.add(dto.getBodova().toString());
			rezultati.dodajRezultat(red, podaci, bojaOkvira, opaque);
			red++;
		}
		panelLista.add(rezultati, BorderLayout.CENTER);
		
		return;
	}
	

	public RangListaDto dajRangListu(RezultatDto rezdto) {
		RangListaDto rangdto = new RangListaDto();
		try {
			conn = PostgreConn.dajKonekciju();
			RezultatSql rezsql = new RezultatSql(conn);
			rezdto.setIdRezultata(rezsql.dajMaxIdRezultata());
			rezsql.unosRezultata(rezdto);
			List<RezultatDto> rangLista = rezsql.dajListuTop10();
			rangdto.setRangLista(rangLista);
		} catch (Exception e) {
			rangdto.setPozicija(0);
			rangdto.setInfo("Dogodila se greška kod dohvata rang liste");
			rangdto.setRangLista(new ArrayList<RezultatDto>());
		} finally {
			KvizSqlUtil.close(conn);
		}
		return rangdto;
	}
		
}
