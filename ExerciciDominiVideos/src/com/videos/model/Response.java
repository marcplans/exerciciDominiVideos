package com.videos.model;

public class Response<T> {
	private T t;
	private String responseMessage;
	private boolean exceptionThrown;
	private String exceptionMessage;
		
	// Constructors
	public Response() {
		this(null, "", false, "");
	}

	public Response(T t) {
		this(t, "", false, "");
	}

	public Response(String responseMessage) {
		this(null, responseMessage, false, "");
	}

	public Response(T t, String responseMessage) {
		this(t, responseMessage, false, "");
	}

	public Response(String exceptionMessage, boolean exceptionThrown) {
		this(null, "e", exceptionThrown, exceptionMessage);
	}

	public Response(T t, String responseMessage, boolean exceptionThrown,
			String exceptionMessage) {
		this.t = t;
		this.responseMessage = responseMessage;
		this.exceptionThrown = exceptionThrown;
		this.exceptionMessage = exceptionMessage;
	}
	
	// Getters and setters
	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public final String getResponseMessage() {
		return responseMessage;
	}

	public final void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public final boolean isExceptionThrown() {
		return exceptionThrown;
	}

	public final void setExceptionThrown(boolean exceptionThrown) {
		this.exceptionThrown = exceptionThrown;
	}

	public final String getExceptionMessage() {
		return exceptionMessage;
	}

	public final void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	public String toString() {
		return "Response content: " + get() + "\nResponse message: "
				+ getResponseMessage() + "\nException trown: " + exceptionThrown
				+ "\nException message: " + exceptionMessage;
	}

}
