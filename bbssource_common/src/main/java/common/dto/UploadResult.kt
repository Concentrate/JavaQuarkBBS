package common.dto

import java.io.Serializable

/**
 * Created by liudeyu on 2019/6/30.
 */


class UploadResult() : Serializable {

    constructor(status: Int) : this() {
        this.status = status
    }


    class UploadData {
        var title = ""
        var src = ""

        constructor(t: String, url: String) {
            title = t;
            src = url;
        }
    }

    var status = 0
    var data: UploadData? = null
    var errorMessage = "" // error message

}