package hr.kviz.manager;

import java.sql.Connection;

import hr.kviz.dto.NatjecateljDto;
import hr.kviz.sql.NatjecateljSql;
import hr.kviz.sql.PostgreConn;
import hr.kviz.sql.KvizSqlUtil;

public class PrijavaManager {

	private Connection conn;
	
	
	public NatjecateljDto checkNatjecatelj(String idPrijave) throws Exception {
		System.out.println("checkNatjecatelj " + idPrijave);
		NatjecateljDto dto = dajNatjecatelja(idPrijave);
		dto.setLozinka("**********"); //sakrij lozinku
		return dto;
	}

	public boolean checkNatjecatelj(NatjecateljDto natjecatelj, char[] ch) throws Exception {
		System.out.println("checkNatjecatelj " + natjecatelj.getIme());
		NatjecateljDto dto = dajNatjecatelja(natjecatelj.getIdPrijave());
		
		String str = String.valueOf(ch);
		boolean prijavaOK = dto.getLozinka().equals(str);
		return prijavaOK;
	}
	
	
	private NatjecateljDto dajNatjecatelja(String idPrijave) throws Exception {
		NatjecateljDto natjecatelj = null;
		try {
			conn = PostgreConn.dajKonekciju();
			NatjecateljSql sql = new NatjecateljSql(conn);
			natjecatelj = sql.dajNatjecatelja(idPrijave);
		} catch (Exception e) {
			System.out.println("Greška=" + e.getMessage());
			throw new Exception("checkNatjecatelj, err=" + e.getMessage());
		} finally {
			KvizSqlUtil.close(conn);
		}
		return natjecatelj;
	}

	public NatjecateljDto unosNatjecatelja(String idPrijava, String ime, char[] chPass) throws Exception {
		NatjecateljDto natjecatelj = null;
		try {
			conn = PostgreConn.dajKonekciju();
			NatjecateljSql sql = new NatjecateljSql(conn);
			int idNatj = sql.dajMaxIdNatjecatelja();
			sql.noviUnos(idNatj, idPrijava, ime, new String(chPass));
			natjecatelj = sql.dajNatjecatelja(idPrijava);
		} catch (Exception e) {
			System.out.println("Greška=" + e.getMessage());
			throw new Exception("unosNatjecatelja, err=" + e.getMessage());
		} finally {
			KvizSqlUtil.close(conn);
		}
		return natjecatelj;	
	}
	
}































