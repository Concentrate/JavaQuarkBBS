package common.dto

import java.io.Serializable

/**
 * Created by liudeyu on 2019/6/30.
 */
class SocketMessage : Serializable {
    var message: String = ""
    var code = 0
}