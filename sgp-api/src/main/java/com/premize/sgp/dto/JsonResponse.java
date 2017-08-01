/**
 * 
 */
package com.premize.sgp.dto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class JsonResponse
 * @since 6/02/2014
 */
public class JsonResponse {
	private String status = null;
	private Object result = null;
	private Object data = null;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(Object result) {
		this.result = result;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
}
