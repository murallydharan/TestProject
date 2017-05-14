package org.unico.sample.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.unico.sample.model.ErrorMessage;

	@Provider
	public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

		@Override
		public Response toResponse(Throwable excep) {
			ErrorMessage objMessage = new ErrorMessage(999, "Tech Error:"+excep.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(objMessage)
					.build();
			//return Response.status(400).build();
		}
	}