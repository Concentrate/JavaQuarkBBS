package common.baseservice;

import common.dto.QuarkResult;
import common.exceptions.ApiException;

/**
 * Created by liudeyu on 2019/6/30.
 */
public class BaseController {

    public QuarkResult process(Processor processor) {
        try {
            return processor.process();
        } catch (ApiException ex) {
            return QuarkResult.Companion.error(ex.getStackTrace().toString());
        } catch (Exception ex) {
            return QuarkResult.errorSystem(ex.getStackTrace().toString());
        }
    }

}
