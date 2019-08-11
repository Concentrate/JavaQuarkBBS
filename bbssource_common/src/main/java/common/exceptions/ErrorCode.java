package common.exceptions;

/**
 * Created by liudeyu on 2019/8/11.
 */
public class ErrorCode {

    public static final int COMMON_REQUEST_ERROR_CODE = 1001;

    public interface RequestStateError {
        int USER_NOT_LOGIN_ERROR = 10001;
        int USER_FORBIDDEN_ERROR = 10002;
        int INVAILED_PARAMS = 20002;

    }


    public interface ServerStateError {
        int INTERNAL_SERVER_ERROR = 20003;
    }

    public interface ErrorReasonDes{
        String TOKEN_EXPIRE = "token expired";
        String USER_NOT_LOGIN = "user not login in";
        String REQUEST_PARAM_WRONOG = "request params errorApi";

    }


}
