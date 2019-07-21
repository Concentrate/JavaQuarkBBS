package common.exceptions;

/**
 * Created by liudeyu on 2019/6/30.
 */
public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
