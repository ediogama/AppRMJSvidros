package br.com.ope_rmjs_vidros

import android.content.Context
import android.util.Log
import br.com.ope_rmjs_vidros.modelo.Fornecedor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FornecedorService {
    val host = "http://ediogama.pythonanywhere.com"
    val TAG = "WS_RMJSVidros"

    fun getFornecedores (context: Context) : List<Fornecedor> {
        val url = "$host/fornecedores"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        return parserJson(json)

    }

    fun save(fornecedor: Fornecedor): Response {
        var json = HttpHelper.post("$host/fornecedores", fornecedor.toJson())
        return parserJson(json)

    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
