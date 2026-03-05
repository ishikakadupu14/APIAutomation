package automationprojects.APIAutomation.PageObjects;

import java.util.List;

public class EventsResponse {

	String success;
	EventData data;
	String message;
	Pagination pagination;
	
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public EventData getData() {
		return data;
	}
	public void setData(EventData data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
	
	
}
