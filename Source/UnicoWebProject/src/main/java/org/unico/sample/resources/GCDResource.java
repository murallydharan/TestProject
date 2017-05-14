package org.unico.sample.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unico.sample.model.GcdModel;
import org.unico.sample.service.GCDService;

@Path("gcd")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GCDResource {
	
	private GCDService gcdService = new GCDService();
	
	@GET
	public List<GcdModel> getGCDList(){
		//System.out.println("Invoked Get Stub");
		return gcdService.getGcdList();
		//return gcdService.getGcdListStub();
	}

	@POST
	@Path("/{number1}/{number2}")
	public Response pushGcdData(@PathParam("number1") int number1, @PathParam("number2") int number2 ){
		//System.out.println("Post Invoked");
		String status = null;
		GcdModel gcdModel = new GcdModel(number1, number2);
		status = gcdService.pushGcdListStub(gcdModel);
		return Response.status(200).entity(status).build();
	}
	
	@POST
	public Response pushGcdData(GcdModel gcd ){
		String status = null;
		status = gcdService.pushGcdList(gcd);
		return Response.status(200).entity(status).entity(gcd).build();
	}	
}
