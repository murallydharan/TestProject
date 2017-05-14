package org.unico.sample.business.jms;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Queue;
import java.util.ResourceBundle;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.unico.sample.model.GcdModel;

public class JMSPoster {
	private QueueConnectionFactory connFactory;
	private QueueConnection conn; 
	private Queue queue;
	private QueueSender queueSender;
	private QueueSession session;
	private ObjectMessage objMessage;

	public String QueuePoster(GcdModel objGcdModel) throws Exception{
		Hashtable<Object,String> envDtls = null;
		Context ctx = null;
		String provideUrl = null;
		String contextFactory = null;
		String queueConnFactory = null;
		String queueName = null;
		objMessage = null;
		String status = null;
		
		try{
			provideUrl = getProperty("JMS_SERVER_URL");
			contextFactory = getProperty("CONTEXT_FACTORY");
			queueConnFactory = getProperty("JMS_CONN_FACTORY_GCD");
			queueName = getProperty("GCD_QUEUE");
			
			envDtls = new Hashtable<Object,String>();
			envDtls.put(Context.INITIAL_CONTEXT_FACTORY,contextFactory);
			envDtls.put(Context.PROVIDER_URL, provideUrl);

			ctx = new InitialContext(envDtls);
			connFactory = (QueueConnectionFactory)ctx.lookup(queueConnFactory);
			conn = connFactory.createQueueConnection();
			session = conn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup(queueName);
			queueSender = session.createSender((javax.jms.Queue) queue);
			objMessage = session.createObjectMessage();
			objMessage.setObject((Serializable) objGcdModel);
			queueSender.send(objMessage);
			status = "Message Posted To Queue";
		}catch(JMSException JMSEx){
			JMSEx.printStackTrace();
			status = "JMSException -->"+JMSEx.getMessage();
			throw JMSEx;
		}catch(Exception ex){
			ex.printStackTrace();
			status = "Exception -->"+ex.getMessage();
			throw ex;
		}finally{
			try{
				if(queueSender != null)
					queueSender.close();
				if(session != null)
					session.close();
				if(conn != null)
					conn.close();
			}catch(JMSException JMSEx){
				JMSEx.printStackTrace();
			} 
		}
		return status;

	}
	
	public static String getProperty(String key){
		ResourceBundle config = null;
		String propValue = "";
		try{
			config = ResourceBundle.getBundle("AppConfig");
			propValue = config.getString(key);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			config = null;			
		}
		return propValue;
	}	
	
	
}
