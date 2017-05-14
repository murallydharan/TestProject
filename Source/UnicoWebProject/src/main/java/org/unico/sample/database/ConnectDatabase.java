package org.unico.sample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.unico.sample.business.jms.JMSPoster;

public class ConnectDatabase {
	
	public static Connection openDirectConnection(){
		Connection conn = null;
		try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","CORE_SCHEMA","CORE_SCHEMA");
		}catch(Exception e){
			System.out.println("Unable to open database connection. "+ e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
    public static Connection getLookupConnection() {
    	Connection conn = null;
        try{
        	Context ctx = null;
        	  Hashtable<Object,String> ht = new Hashtable<Object,String>();
        	  ht.put(Context.INITIAL_CONTEXT_FACTORY,
        	         JMSPoster.getProperty("CONTEXT_FACTORY"));
        	  ht.put(Context.PROVIDER_URL,
        			  JMSPoster.getProperty("CONN_T3_URL"));

        	  ctx = new InitialContext(ht);
        	  javax.sql.DataSource ds  = (javax.sql.DataSource) ctx.lookup (JMSPoster.getProperty("CONN_DATASRC_JNDI"));
        	  conn = ds.getConnection();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return conn;
    }
}
