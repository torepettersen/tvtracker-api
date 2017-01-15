package tvtracker.domain;

public class Response {
	
	private String message;
	
	private int status;
	
	// Constructors
	public Response() {
		
	}

	public Response(String message, int status) {
		this.message = message;
		this.status = status;
	}

	// Setters and getters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
