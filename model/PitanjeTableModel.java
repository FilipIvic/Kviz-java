package hr.kviz.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import hr.kviz.dto.PitanjeDto;

public class PitanjeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -48842616069267879L;

	private List<PitanjeDto> listaPitanja;

	private final String[] columnNames = new String[] { 
		"Područje" //, "Pitanje"
	};
	
    private final Class<?>[] columnClass = new Class[] {
            String.class //, String.class
        };
	
	public PitanjeTableModel(List<PitanjeDto> listaPitanja) {
		this.listaPitanja = listaPitanja;
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
		return (listaPitanja == null) ? 0 : listaPitanja.size();
	}

	@Override
	public Object getValueAt(int redIndex, int kolIndeks) {
		PitanjeDto dto = getPitanjeDto(redIndex);
		Object value = null;
		if (kolIndeks == 0) {
			value = dto.getTekst();
		/*} else if (kolIndeks == 1) {
			value = dto.getTekst();*/
		}
		return value;
	}

	public PitanjeDto getPitanjeDto(int redIndeks) {
		if (redIndeks < 0) {
			return new PitanjeDto();
		}
		return listaPitanja.get(redIndeks);
	}
	
	public void setPitanjeDto(PitanjeDto dto) {
		int redIndex = listaPitanja.indexOf(dto);
		listaPitanja.set(redIndex, dto);
		fireTableRowsUpdated(redIndex, redIndex);
		return;
	}

	public void addPitanjeDto(PitanjeDto dto) {
		listaPitanja.add(dto);
		fireTableDataChanged();
		return;
	}

	public void setListaPitanja(List<PitanjeDto> listaPitanja) {
		this.listaPitanja = listaPitanja;
		fireTableDataChanged();
		return;
	}
	
}
