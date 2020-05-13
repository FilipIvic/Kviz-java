package hr.kviz.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import hr.kviz.dto.OdgovorCompareDto;
import hr.kviz.dto.PitanjeCompareDto;

public class KvizUtil {

	public static void centreWindow(Component frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    return;
	}

	
	/**
	 * Određivanje pojedinačne širine kolone u tabeli, zadnju kolonu automatski proširiti
	 * @param table tabela sa kolonama
	 * @param widths širina kolone 1, 2, 3, ..., do n
	 */
	public static void setColumnWidths(JTable table, int... widths) {
	    TableColumnModel columnModel = table.getColumnModel();
	    for (int i = 0; i < widths.length; i++) {
	        if (i < columnModel.getColumnCount()) {
	            columnModel.getColumn(i).setMaxWidth(widths[i]);
	        } else break;
	    }
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	    return;
	}

	/**
	 * Određeni testni set pitanja za testiranje apliakcije
	 * @return
	 */
	public static List<PitanjeCompareDto> dajTestnaPitanja() {
		List<PitanjeCompareDto> lista = new ArrayList<PitanjeCompareDto>();
		SlucajniBroj sbroj = new SlucajniBroj();
		for (int i = 0; i < 22; i++) {
			PitanjeCompareDto dto = new PitanjeCompareDto();
			dto.setIdPitanja(new Integer(i));
			dto.setTekst("Ovo je testno pitanje broj " + i);
			dto.setOpisPodrucja("Područje " + (i % 7));
			dto.setCompareId(sbroj.nextBD());
			dto.setListaOdgovora(new ArrayList<OdgovorCompareDto>());
			for (int j = 0; j < 4; j++) {
				OdgovorCompareDto odg = new OdgovorCompareDto();
				odg.setRedniBroj(new Integer(j + 1));
				odg.setTekst((j == 0) ? "Točan odgovor" : "Netočan odgovor " + j);
				odg.setCompareId(sbroj.nextBD());
				dto.getListaOdgovora().add(odg);
			}
			Collections.sort(dto.getListaOdgovora());
			lista.add(dto);
		}
		Collections.sort(lista);
		return lista;
	}
	
}
