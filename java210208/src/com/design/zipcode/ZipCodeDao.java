package com.design.zipcode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.util.DBConnectionMgr;
public class ZipCodeDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = null;
	public ZipCodeDao() {
		dbMgr = DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnection();
		} catch (Exception e) {
			System.out.println("오라클 서버 연결 실패");
		}
	}
	public ArrayList<ZipCodeVO> getZipCodeList(String dong){
		System.out.println("getZipCodeList 호출 성공"+ dong);
		ArrayList zipcodeList = null;
		return zipcodeList;
	}
}	


