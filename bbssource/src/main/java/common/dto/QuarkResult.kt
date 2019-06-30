package common.dto

import com.google.common.net.HttpHeaders
import com.sun.deploy.net.HttpUtils
import com.sun.net.httpserver.HttpServer
import org.omg.CORBA.Object
import java.io.Serializable
import java.net.HttpURLConnection
import javax.xml.ws.spi.http.HttpHandler

/**
 * Created by liudeyu on 2019/6/30.
 */
class QuarkResult() : Serializable {


    companion object {

        fun ok(data: Any): QuarkResult {
            val data = QuarkResult(HttpURLConnection.HTTP_OK, data)
            return data
        }
        fun error(errorMsg:String):QuarkResult{
            return QuarkResult(HttpURLConnection.HTTP_INTERNAL_ERROR,errorMsg)
        }

    }

    constructor(status: Int, data: Any) : this() {
        this.status = status
        this.data = data
    }

    constructor(status: Int, error: String) : this() {
        this.status = status
        this.error = error
    }

    var status = 0;
    var data: Any? = null
    var error: String = ""
    var pageSize: Int = 0
    var total: Int = 0


}