package hr.kviz.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.kviz.dto.OdgovorDto;

public class OdgovorSql {

	private Connection conn = null;

	public OdgovorSql(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<OdgovorDto> dajListuOdgovoraNaPitanje(Integer idPitanja) throws SQLException {
		
		List<OdgovorDto> lista = new ArrayList<OdgovorDto>();
		
		String sql = "SELECT redni_broj, tekst "
				+ "FROM odgovor "
				+ "WHERE id_pitanja = " + idPitanja
				+ "order by redni_broj ";
		
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			OdgovorDto dto = new OdgovorDto();
			dto.setIdPitanja(idPitanja);
			dto.setRedniBroj(rs.getInt("redni_broj"));
			dto.setTekst(rs.getString("tekst"));
			lista.add(dto);
		}
		return lista;
	}

	public void novi(int idPitanja, List<OdgovorDto> listaOdgovora) throws SQLException {
		
		String sql = "INSERT INTO public.odgovor(" + 
				"id_pitanja, redni_broj, tekst) " + 
				"VALUES (?, ?, ?)";
		
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idPitanja);
			for (OdgovorDto dto : listaOdgovora) {
				pst.setInt(2, dto.getRedniBroj());
				pst.setString(3, dto.getTekst());
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("SQL error=" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException ignore) {	}
			}
		}
		return;
	}

	public void izmjeni(Integer idPitanja, List<OdgovorDto> listaOdgovora) throws SQLException {
		String sql = "UPDATE public.odgovor " + 
				"SET tekst=? " + 
				"WHERE id_pitanja=? " +
				"AND redni_broj=? ";
		
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(2, idPitanja);
			for (OdgovorDto dto : listaOdgovora) {
				pst.setInt(3, dto.getRedniBroj());
				pst.setString(1, dto.getTekst());
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("SQL error=" + e.getMessage());
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException ignore) {	}
			}
		}
		return;
	}
		
}
