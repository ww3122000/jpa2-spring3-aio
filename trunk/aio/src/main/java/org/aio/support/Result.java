package org.aio.support;


import java.util.List;

/**
 * Result.java
 * @author seven
 * 
 * 2010-12-5
 */
public class Result {
	private int total;
	private List<?> rows;
	private boolean success = true;
	private Object data;
    
	public Result(){}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setSuccess(String msg) {
		this.success = true;
		this.data=msg;
	}
	public void setError(String msg) {
		this.success = false;
		this.data=msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}