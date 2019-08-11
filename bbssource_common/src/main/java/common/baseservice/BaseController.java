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
            return QuarkResult.errorStausCode(ex.getStatusCode(), ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return QuarkResult.errorSystem("system errorApi or params wrong");
        }
    }

}
