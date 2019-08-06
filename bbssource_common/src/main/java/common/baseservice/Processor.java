package common.baseservice;

import common.dto.QuarkResult;

import java.io.IOException;

/**
 * Created by liudeyu on 2019/6/30.
 */
public interface Processor {

    QuarkResult process() throws IOException;
}
