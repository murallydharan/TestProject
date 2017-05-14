package org.unico.sample.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.unico.sample.model.ErrorMessage;

@Provider
public class GcdExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException excep) {
		ErrorMessage objMessage = new ErrorMessage(400, "No GCD Calculations Happened. No Records Found.");
		return Response.status(Status.NOT_FOUND)
				.entity(objMessage)
				.build();
		//return Response.status(400).build();
	}
}
