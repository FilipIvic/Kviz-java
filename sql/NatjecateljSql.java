package hr.kviz.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hr.kviz.dto.NatjecateljDto;

public class NatjecateljSql {

	private Connection conn = null;

	public NatjecateljSql(Connection conn) {
		super();
		this.conn = conn;
	}

	public NatjecateljDto dajNatjecatelja(String idPrijave) throws SQLException {
		
		NatjecateljDto natjecatelj = new NatjecateljDto();
		natjecatelj.setIdPrijave(idPrijave);
		
		String sql = "SELECT id_natjecatelja, lozinka, ime, uloga, registracija, datum_prijave "
				+ "FROM natjecatelj "
				+ "WHERE id_prijave = '" + idPrijave + "'";
		System.out.println("sql=" + sql);
		
		Statement stm = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				natjecatelj.setIdNatjecatelja(rs.getInt("id_natjecatelja"));
				natjecatelj.setLozinka(rs.getString("lozinka").trim());
				natjecatelj.setIme(rs.getString("ime"));
				natjecatelj.setUloga(rs.getString("uloga"));
				natjecatelj.setRegistracija(rs.getString("registracija"));
				natjecatelj.setDatumPrijave(rs.getString("datum_prijave"));
			}
		} catch (Exception e) {
			System.out.println("SQL error=" + e.getMessage());
			throw new SQLException("SQL error=" + e.getMessage());
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return natjecatelj;
	}

	public void noviUnos( int idNatj, String idPrijava, String ime, String lozinka) throws SQLException {
		String sql = "INSERT INTO public.natjecatelj( " + 
				"id_natjecatelja, id_prijave, lozinka, ime, uloga, registracija, datum_prijave) " + 
				"VALUES (?, ?, ?, ?, 'N', current_date, current_date)";
		
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNatj);
			pst.setString(2, idPrijava);
			pst.setString(3, lozinka);
			pst.setString(4, ime);
			pst.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("sql error:" + e.getMessage());
		} finally {
			KvizSqlUtil.close(pst);
		}
		return;
	}

	public Integer dajMaxIdNatjecatelja() throws SQLException {
		int maxIdNatj = 0;
		String sql = "SELECT MAX(id_natjecatelja) as maxid "
				+ "FROM natjecatelj ";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		if (rs.next()) {
			maxIdNatj = rs.getInt("maxid");
		}
		maxIdNatj++;
		return maxIdNatj;
	}

	
}
