package common.baseservice;

import common.dto.QuarkResult;
import common.exceptions.ApiException;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by liudeyu on 2019/6/30.
 */
public class BaseController {

    public QuarkResult process(Processor processor) {
        try {
            return processor.process();
        } catch (ApiException ex) {
            return QuarkResult.error(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
//            StringBuilder builder = new StringBuilder();
//            Arrays.stream(ex.getStackTrace()).forEach(stackTraceElement -> {
//                builder.append(stackTraceElement.getClassName() + "-" +
//                        stackTraceElement.getMethodName() + ": " + stackTraceElement.getLineNumber())
//                        .append("\n");
//            });
            return QuarkResult.errorSystem("system error or params wrong");
        }
    }

}
