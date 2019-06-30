package common.dto

import java.io.Serializable

/**
 * Created by liudeyu on 2019/6/30.
 */
class PageResult<T> : Serializable {
    var totalRecord = 0
    var filterRecord = 0
    var draw = 0
    var data: T? = null

}