package common.exceptions;

/**
 * Created by liudeyu on 2019/6/30.
 */
public class ApiException extends RuntimeException {


    int statusCode;

    public ApiException(String message) {
        super(message);
        statusCode = ErrorCode.COMMON_REQUEST_ERROR_CODE;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }


}
