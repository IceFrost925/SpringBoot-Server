package com.xzit.project.utils;

public class ResultObj {
    private boolean result = true;   //业务是否失败
    private int code;         //返回码
    private String msg;       //附加信息
	private Object data;      //返回数据

	public ResultObj(){

	}

    public ResultObj(boolean result, int code, String msg, Object data) {
        this.result = result;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultObj{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            ", data=" + data +
            '}';
    }
    public static ResultObj back(int code,String msg,Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(code);
        resultObj.setMsg(msg);
        resultObj.setData(data);
        return resultObj;
    }
    public static ResultObj back(int code,Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(code);
        resultObj.setMsg(ErrorCode.getErrorCode(code));
        resultObj.setData(data);
        return resultObj;
    }
    public static ResultObj back(boolean result,int code,Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(code);
        resultObj.setData(data);
        resultObj.setMsg(ErrorCode.getErrorCode(code));
        resultObj.setResult(result);
        return resultObj;
    }

    public static ResultObj backInfo(boolean result,int code,String msg,Object data){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(code);
        resultObj.setMsg(msg);
        resultObj.setData(data);
        resultObj.setResult(result);
        return resultObj;
    }

    /**
     * CRUD操作成功
     * @param msg
     * @return
     */
    public static ResultObj backCRUDSuccess(String msg){
        return new ResultObj(true, 200, msg, null);
    }

    /**
     * CRUD操作失败
     * @param msg
     * @return
     */
    public static ResultObj backCRUDError(String msg){
        return new ResultObj(false, 200, msg, null);
    }

    public String toJsonString() {
        return "{\"code\": "+
            code + ", " +
            "\"msg\": " + "\"" +msg + "\""+
            "}";
    }
}
