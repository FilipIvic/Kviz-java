package hr.kviz.manager;

import java.sql.Connection;

import hr.kviz.dto.PodrucjeDto;
import hr.kviz.sql.PodrucjeSql;
import hr.kviz.sql.PostgreConn;
import hr.kviz.sql.KvizSqlUtil;

public class PodrucjeManager {

	private Connection conn;

	public PodrucjeDto novi(String opis) {
		PodrucjeDto dto = null;
		try {
			conn = PostgreConn.dajKonekciju();
			PodrucjeSql sql = new PodrucjeSql(conn);
			
			dto = new PodrucjeDto();
			dto.setIdPodrucja(sql.dajMaxIdPodrucja());
			dto.setOpis(opis);
			sql.novi(dto);
			
		} catch (Exception e) {
			System.out.println("insertOrUpdate, err=" + e.getMessage());			
		} finally {
			KvizSqlUtil.close(conn);
		}
		return dto;
	}

	public void izmjeni(PodrucjeDto dto) {
		try {
			conn = PostgreConn.dajKonekciju();
			PodrucjeSql sql = new PodrucjeSql(conn);
			sql.izmjeni(dto);
		} catch (Exception e) {
			System.out.println("insertOrUpdate, err=" + e.getMessage());			
		} finally {
			KvizSqlUtil.close(conn);
		}
		return;
	}
	
}
