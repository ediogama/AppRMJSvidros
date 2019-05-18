package br.com.ope_rmjs_vidros.services

import android.content.Context
import android.util.Log
import br.com.ope_rmjs_vidros.Cliente
import br.com.ope_rmjs_vidros.helpers.HttpHelper
import br.com.ope_rmjs_vidros.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ClienteService {
    val host = "http://ediogama.pythonanywhere.com"
    val TAG = "WS_RMJSVidros"

    fun getClientes (context: Context) : List<Cliente> {
        val url = "$host/clientes"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        return parserJson(json)

    }

    fun save(cliente: Cliente?): Response {
        var json = HttpHelper.post(
            "$host/clientes",
            cliente!!.toJson()
        )
        return parserJson(json)

    }

    fun delete(cliente: Cliente): Response {

        val url = "$host/cliente/${cliente.id}"
        val json = HttpHelper.delete(url)

        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}