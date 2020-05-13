package hr.kviz.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hr.kviz.dto.RezultatDto;

public class RezultatTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -48842616069267879L;

	private List<RezultatDto> listaRezultata;

	private final String[] columnNames = new String[] { 
		"Poredak", "Natjecatelj", "Vrijeme", 
		"Pitanja", "Točno", "Bodova"
	};
	
    private final Class<?>[] columnClass = new Class[] {
            Integer.class, String.class, Integer.class, 
            Integer.class, Integer.class, Integer.class
        };
	
	public RezultatTableModel(List<RezultatDto> listaRezultata) {
		this.listaRezultata = listaRezultata;
	}

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
	
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
 
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return (listaRezultata == null) ? 0 : listaRezultata.size();
	}

	@Override
	public Object getValueAt(int redIndex, int kolIndeks) {
		RezultatDto dto = getPitanjeDto(redIndex);
		Object value = null;
		if (kolIndeks == 0) {
			value = dto.getPoredak();
		} else if (kolIndeks == 1) {
			value = dto.getIme().trim();
		} else if (kolIndeks == 2) {
			value = dto.getVrijeme();
		} else if (kolIndeks == 3) {
			value = dto.getBrojPitanja();
		} else if (kolIndeks == 4) {
			value = dto.getBrojTocnih();
		} else if (kolIndeks == 5) {
			value = dto.getBodova();
		} else {
			value = "";
		}
		return value;
	}

	public RezultatDto getPitanjeDto(int redIndeks) {
		if (redIndeks < 0) {
			return new RezultatDto();
		}
		return listaRezultata.get(redIndeks);
	}
	
	public void setPitanjeDto(RezultatDto dto) {
		int redIndex = listaRezultata.indexOf(dto);
		listaRezultata.set(redIndex, dto);
		fireTableRowsUpdated(redIndex, redIndex);
		return;
	}

	public void addPitanjeDto(RezultatDto dto) {
		listaRezultata.add(dto);
		fireTableDataChanged();
		return;
	}

	public void setListaRezultata(List<RezultatDto> listaRezultata) {
		this.listaRezultata = listaRezultata;
		fireTableDataChanged();
		return;
	}
	
}
