/*
** Name : Muraly
** For : Unico Systems
*/

package org.unico.queue.listener;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.unico.sample.database.GCDDAO;
import org.unico.sample.model.GcdModel;

@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "GcdQueue"),
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "GcdQueue")
public class GcdMessageProcessor implements MessageListener {
	
	@Resource
	private MessageDrivenContext mdc;

    public GcdMessageProcessor() {
    }
	
    public void onMessage(Message message) {
		try {
			if (message instanceof Object) {
				GcdModel objmsg = (GcdModel) message;
				doProcess(objmsg);
			}else{
				System.out.println("In Appropriate Message");
			}
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    public void doProcess(GcdModel gcd){
    	GCDDAO gcdDao = null;
    	try{
    		gcdDao = new GCDDAO();
    		if(gcdDao.pushGcdList(gcd))
    			System.out.println("Processed Successfully");
    		else
    			System.out.println("Message Not Processed Successfully");
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

}
