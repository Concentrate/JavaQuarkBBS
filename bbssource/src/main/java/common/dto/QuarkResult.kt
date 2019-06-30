package common.dto

import org.omg.CORBA.Object
import java.io.Serializable

/**
 * Created by liudeyu on 2019/6/30.
 */
class QuarkResult() : Serializable {
    var status = 0;
    var data: Object? = null
    var error: String = ""


}