package br.com.ope_rmjs_vidros.services

import android.content.Context
import android.util.Log
import br.com.ope_rmjs_vidros.AndroidUtils
import br.com.ope_rmjs_vidros.helpers.HttpHelper
import br.com.ope_rmjs_vidros.Response
import br.com.ope_rmjs_vidros.dao.DatabaseManager
import br.com.ope_rmjs_vidros.modelo.Orcamento
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OrcamentoService {
    val host = "http://ediogama.pythonanywhere.com"
    val TAG = "WS_RMJSVidros"
    val dao = DatabaseManager.getOrcamentoDAO()

    fun getOrcamentos (context: Context) : List<Orcamento> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/orcamentos"
            val json = HttpHelper.get(url)
            val orcamentos: List<Orcamento> = parserJson(json)

            for (o in orcamentos) {
                if (dao.getById(o.id) == null) {
                    dao.insert(o)
                }
            }

            Log.d(TAG, json)

            return orcamentos
        }
        else {
            return dao.findAll()
        }
    }

    fun save(orcamento: Orcamento): Response {
        var json = HttpHelper.post(
            "$host/orcamentos",
            orcamento.toJson()
        )
        return parserJson(json)

    }

    fun delete(orcamento: Orcamento): Response {

        val url = "${OrcamentoService.host}/orcamentos/${orcamento.id}"
        val json = HttpHelper.delete(url)

        return OrcamentoService.parserJson(json)
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
