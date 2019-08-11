package common.dto;

import common.exceptions.ErrorCode;

import java.util.Date;

public class QuarkResult {

    public static final int SUCCESS_CODE = 0;


    public static class Extra {

        private String errorMsg;
        private int pageSize;
        private int total;
        private long timpStamp = new Date().getTime();
        private boolean hasMore;

        public long getTimpStamp() {
            return timpStamp;
        }

        public boolean isHasMore() {
            return hasMore;
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
    }

    private int status;
    private Object data;
    private Extra extra = new Extra();

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

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

    public void setErrorMsg(String errorMsg) {
        extra.setErrorMsg(errorMsg);
    }

    public void setPageSize(int pageSize) {
        extra.setPageSize(pageSize);
    }


    public void setTotal(int total) {
        extra.setTotal(total);
    }

    public QuarkResult(int status, String errorMsg) {
        this.status = status;
        extra.setErrorMsg(errorMsg);
    }

    public QuarkResult(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static QuarkResult ok(Object tmp) {
        QuarkResult data = new QuarkResult(SUCCESS_CODE, tmp);
        return data;
    }

    public static QuarkResult ok() {
        return new QuarkResult(SUCCESS_CODE, null);
    }

    public static QuarkResult errorApi(String errorMsg) {
        return new QuarkResult(ErrorCode.COMMON_REQUEST_ERROR_CODE, errorMsg);
    }

    public static QuarkResult errorStausCode(int status, String errorMsg) {
        return new QuarkResult(status, errorMsg);
    }


    public static QuarkResult errorSystem(String errorMsg) {
        return new QuarkResult(ErrorCode.ServerStateError.INTERNAL_SERVER_ERROR
                , errorMsg);
    }


}
