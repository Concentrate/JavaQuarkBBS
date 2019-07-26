import java.util.concurrent.TimeUnit;

/**
 * Created by liudeyu on 2019/7/25.
 */
public class SleepUtil {
    public static void sleepSecond(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
