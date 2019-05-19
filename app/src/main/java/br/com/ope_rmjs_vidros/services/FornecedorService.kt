package br.com.ope_rmjs_vidros.services

import android.content.Context
import android.util.Log
import br.com.ope_rmjs_vidros.AndroidUtils
import br.com.ope_rmjs_vidros.helpers.HttpHelper
import br.com.ope_rmjs_vidros.Response
import br.com.ope_rmjs_vidros.dao.DatabaseManager
import br.com.ope_rmjs_vidros.modelo.Fornecedor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FornecedorService {
    val host = "http://ediogama.pythonanywhere.com"
    val TAG = "WS_RMJSVidros"
    val dao = DatabaseManager.getFornecedorDAO()

    fun getFornecedores (context: Context) : List<Fornecedor> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/fornecedores"
            val json = HttpHelper.get(url)
            val fornecedores: List<Fornecedor> = parserJson(json)

            for (f in fornecedores) {
                if (dao.getById(f.id) == null) {
                    dao.insert(f)
                }
            }

            Log.d(TAG, json)

            return fornecedores
        }
        else {
            return dao.findAll()
        }
    }

    fun save(fornecedor: Fornecedor): Response {
        var json = HttpHelper.post(
            "$host/fornecedores",
            fornecedor.toJson()
        )
        return parserJson(json)

    }

    fun delete(fornecedor: Fornecedor): Response {

        val url = "${FornecedorService.host}/fornecedores/${fornecedor.id}"
        val json = HttpHelper.delete(url)

        return FornecedorService.parserJson(json)
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
