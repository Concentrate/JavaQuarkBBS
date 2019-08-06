package common.baseservice;

import common.dto.QuarkResult;
import common.exceptions.ApiException;

import java.io.IOException;

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
            return QuarkResult.errorSystem(ex.getStackTrace().toString());
        }
    }

}
