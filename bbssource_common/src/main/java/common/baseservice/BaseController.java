package common.baseservice;

import common.exceptions.ApiException;

/**
 * Created by liudeyu on 2019/6/30.
 */
public  class BaseController {

    public void process(Processor processor){
        try {
            processor.process();
        }catch (ApiException ex){


        }catch (Exception ex){

        }
    }

}
