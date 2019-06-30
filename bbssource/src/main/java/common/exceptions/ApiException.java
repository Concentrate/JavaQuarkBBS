package common.exceptions;

/**
 * Created by liudeyu on 2019/6/30.
 */
public class ApiException extends RuntimeException{
    private int status;
    private Throwable throwable;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
