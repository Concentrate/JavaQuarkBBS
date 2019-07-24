package common.dto;

import java.net.HttpURLConnection;

public class QuarkResult {


    private int status;
    private Object data;
    private String errorMsg;
    private int pageSize;
    private int total;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public QuarkResult(int status, String errorMsg) {
        this.status = status;
        this.errorMsg = errorMsg;
    }

    public QuarkResult(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static QuarkResult ok(Object tmp) {
        QuarkResult data = new QuarkResult(HttpURLConnection.HTTP_OK, tmp);
        return data;
    }

    public static QuarkResult ok() {
        return new QuarkResult(HttpURLConnection.HTTP_OK, null);
    }

    public static QuarkResult error(String errorMsg) {
        return new QuarkResult(HttpURLConnection.HTTP_BAD_REQUEST, errorMsg);
    }


    public static QuarkResult errorSystem(String errorMsg) {
        return new QuarkResult(HttpURLConnection.HTTP_INTERNAL_ERROR, errorMsg);
    }


}
