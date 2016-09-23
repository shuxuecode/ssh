package com.zsx.web.model.json;

/**
 * json 模型 用于返回操作结果
 */
@SuppressWarnings("serial")
public class JsonModel implements java.io.Serializable{
	/**
	 * 是否成功
	 */
	private boolean success = false;
	/**
	 * 提示信息
	 */
	private String msg = "";
	/**
	 * 其它信息
	 */
	private Object obj = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
