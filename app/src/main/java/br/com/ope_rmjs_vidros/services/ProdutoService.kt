package br.com.ope_rmjs_vidros.services

import android.content.Context
import android.util.Log
import br.com.ope_rmjs_vidros.Produto
import br.com.ope_rmjs_vidros.helpers.HttpHelper
import br.com.ope_rmjs_vidros.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProdutoService {
    val host = "http://ediogama.pythonanywhere.com"
    val TAG = "WS_RMJSVidros"

    fun getProdutos (context: Context) : List<Produto> {
        val url = "$host/produtos"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        return parserJson(json)

    }

    fun save(produto: Produto): Response {
        var json = HttpHelper.post(
            "$host/produtos",
            produto.toJson()
        )
        return parserJson(json)

    }

    fun delete(produto: Produto): Response {

        val url = "${ProdutoService.host}/produtos/${produto.id}"
        val json = HttpHelper.delete(url)

        return ProdutoService.parserJson(json)
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
