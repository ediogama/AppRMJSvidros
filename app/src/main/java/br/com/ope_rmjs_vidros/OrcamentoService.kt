package br.com.ope_rmjs_vidros

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OrcamentoService {
    val host = "http://ediogama.pythonanywhere.com"
    val TAG = "WS_RMJSVidros"

    fun getOrcamentos (context: Context) : List<Orcamento> {
        val url = "$host/orcamentos"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        return parserJson(json)

    }

    fun save(orcamento: Orcamento): Response {
        var json = HttpHelper.post("$host/orcamentos", orcamento.toJson())
        return parserJson(json)

    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
