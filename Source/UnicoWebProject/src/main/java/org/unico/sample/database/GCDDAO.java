package org.unico.sample.database;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.unico.sample.model.GcdModel;

public class GCDDAO {

	private static Map<Integer,GcdModel> dataMap = new HashMap<Integer,GcdModel>();

	public Map<Integer, GcdModel> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<Integer, GcdModel> dataMap) {
		GCDDAO.dataMap = dataMap;
	}

	String _Select_Gcd_List = "SELECT NO_1, NO_2 FROM GCD_MASTER ORDER BY REF_NO ";
	String _Insert_Gcd_List = "INSERT INTO GCD_MASTER(REF_NO, NO_1, NO_2) VALUES(?, ?, ?)";
	String _Get_Refno_Sequence = "SELECT 'REF'||REFNO_SEQ.NEXTVAL SEQVAL FROM DUAL";
	String _To_Find_Gcd = "SELECT REF_NO, NO_1, NO_2 FROM GCD_MASTER WHERE PROCESS_FLG IS NULL ORDER BY ROWID ";
	String _Update_Process_Gcd = "UPDATE GCD_MASTER SET PROCESS_FLG = 'Y', CALC_GCD = ? WHERE REF_NO = ?";
	String _Get_Sum_Computed_Gcd = "SELECT SUM(CALC_GCD) Sum_Gcd FROM GCD_MASTER WHERE PROCESS_FLG = 'Y'";
	String _Get_Computed_Gcd_List = "SELECT CALC_GCD Gcd_List FROM GCD_MASTER WHERE PROCESS_FLG = 'Y'";

 	public List<GcdModel> getGCDList() throws Exception {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<GcdModel> gcdList = new ArrayList<GcdModel>();
		GcdModel objModel = null;

		try {
			conn = ConnectDatabase.getLookupConnection();
			//conn = ConnectDatabase.openDirectConnection();
			ps = conn.prepareStatement(_Select_Gcd_List);
			rs = ps.executeQuery();
			while(rs.next()) {
				objModel = new GcdModel();
				objModel.setNumber1(rs.getInt(1));
				objModel.setNumber2(rs.getInt(2));
				gcdList.add(objModel);
			}
			System.out.println("GCD List -->"+gcdList.size());
		}catch(Exception objEx) {
			System.err.println("Exception in getting GCD List -->"+objEx.getMessage());
			objEx.printStackTrace();
			throw objEx;
		}finally {
			if (rs!=null)
				rs.close();
			if (ps!=null)
				ps.close();
			if(conn != null)
				conn.close();
		}
		return gcdList;
	}
 	
 	public List<GcdModel> getGCDListStub() throws Exception {
		List<GcdModel> gcdList = new ArrayList<GcdModel>();
		Set<Integer> keys = null;
		System.out.println("Into Get Method Stub");

		try {
			keys = dataMap.keySet();
			for(int i:keys){
				gcdList.add(dataMap.get(i));
			}
			System.out.println("GCD List -->"+gcdList.size());
		}catch(Exception objEx) {
			System.err.println("Exception in getting Transaction Point -->"+objEx.getMessage());
			objEx.printStackTrace();
			throw objEx;
		}
		return gcdList;
	}
 	
 	public boolean pushGcdList(GcdModel gcd) throws SQLException{
 		Connection conn = null;
 		PreparedStatement pStmt = null;
 		int statusCnt = 0;
 		ResultSet rSet = null;
 		String refNo = null;
 		try{
			conn = ConnectDatabase.getLookupConnection();
			//conn = ConnectDatabase.openDirectConnection();
			pStmt = conn.prepareStatement(_Get_Refno_Sequence);
			rSet = pStmt.executeQuery();
			if(rSet.next()){
				refNo = rSet.getString(1);
			}else{
				System.out.println("Error in getting sequence");
				return false;
			}
			rSet.close();
			pStmt.close();
			pStmt = conn.prepareStatement(_Insert_Gcd_List);
			pStmt.setString(1, refNo);
			pStmt.setInt(2, gcd.getNumber1());
			pStmt.setInt(2, gcd.getNumber2());
			statusCnt = pStmt.executeUpdate();
			if(statusCnt != 1)
				return false;
 		}catch(Exception ex){
 			ex.printStackTrace();
 		}finally{
			if (pStmt!=null)
				pStmt.close();
			if(conn != null)
				conn.close();
 		}
 		return true;
 	}

 	public boolean pushGcdListStub(GcdModel gcd){
 		try{
 			dataMap.put(dataMap.size()+1, gcd);
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return false;
 		}
		return true;
 	}
 	
 	public int computeUpdateGcd(){
 		Connection conn = null;
 		PreparedStatement pStmt = null;
 		ResultSet rSet = null;
 		String refNo = null;
 		BigInteger bigInt1, bigInt2, bigInt3;
 		bigInt3 = BigInteger.ZERO;
 		try{
 			conn = ConnectDatabase.getLookupConnection();
 			//conn = ConnectDatabase.openDirectConnection();
 			pStmt = conn.prepareStatement(_To_Find_Gcd);
 			rSet = pStmt.executeQuery();
 			if(rSet.next()){
 				refNo = rSet.getString(1);
 				bigInt1 = new BigInteger(rSet.getString(2));
 				bigInt2 = new BigInteger(rSet.getString(3));
 				bigInt3 = bigInt1.gcd(bigInt2);
 				System.out.println("Calculated GCD -->"+bigInt3.intValue());
 			}else{
 				System.out.println("No Record To Process. Please Push Some records to process.");
 			}
 			rSet.close();
 			pStmt.close();
 			pStmt = conn.prepareStatement(_Update_Process_Gcd);
 			pStmt.setString(1, bigInt3.toString());
 			pStmt.setString(2, refNo);
 			int updStatus = pStmt.executeUpdate();
 			if(updStatus > 1){
 				return -99;
 			}
 		}catch(Exception ex){
 			ex.printStackTrace();
 		}finally{
 			try{
 				if(rSet != null)
 					rSet.close();
 				if(pStmt != null)
 					pStmt.close();
 				if(conn != null)
 					conn.close();
 			}catch(Exception ex){
 				ex.printStackTrace();
 			}
 		}
 		return bigInt3.intValue();
 	}
 	
 	public List<Integer> getComputedGcd() throws Exception {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> gcdList = new ArrayList<Integer>();
		
		try {
			conn = ConnectDatabase.getLookupConnection();
			//conn = ConnectDatabase.openDirectConnection();
			ps = conn.prepareStatement(_Get_Computed_Gcd_List);
			rs = ps.executeQuery();
			while(rs.next()) {
				gcdList.add(rs.getInt(1));
			}
			System.out.println("GCD List -->"+gcdList.size());
		}catch(Exception objEx) {
			System.err.println("Exception in getting GCD List -->"+objEx.getMessage());
			objEx.printStackTrace();
			throw objEx;
		}finally {
			if (rs!=null)
				rs.close();
			if (ps!=null)
				ps.close();
			if(conn != null)
				conn.close();
		}
		return gcdList;
	}
 	
 	public Integer getSumComputedGcd() throws Exception {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Integer gcdSum = new Integer(0);
		try {
			conn = ConnectDatabase.getLookupConnection();
			//conn = ConnectDatabase.openDirectConnection();
			ps = conn.prepareStatement(_Get_Sum_Computed_Gcd);
			rs = ps.executeQuery();
			while(rs.next()) {
				gcdSum = rs.getInt(1);
			}
			System.out.println("GCD Sum -->"+gcdSum);
		}catch(Exception objEx) {
			System.err.println("Exception in getting GCD List -->"+objEx.getMessage());
			objEx.printStackTrace();
			throw objEx;
		}finally {
			if (rs!=null)
				rs.close();
			if (ps!=null)
				ps.close();
			if(conn != null)
				conn.close();
		}
		return gcdSum;
	}
 	
}
