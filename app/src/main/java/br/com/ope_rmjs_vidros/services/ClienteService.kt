package br.com.ope_rmjs_vidros.services

import android.content.Context
import br.com.ope_rmjs_vidros.AndroidUtils
import br.com.ope_rmjs_vidros.RMJSApplication
import br.com.ope_rmjs_vidros.helpers.HttpHelper
import br.com.ope_rmjs_vidros.Response
import br.com.ope_rmjs_vidros.dao.DatabaseManager
import br.com.ope_rmjs_vidros.modelo.Cliente
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ClienteService {
    val host = "http://ediogama.pythonanywhere.com"
    val TAG = "WS_RMJSVidros"
    val dao = DatabaseManager.getClienteDAO()

    fun getClientes (context: Context) : List<Cliente> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/clientes"
            val json = HttpHelper.get(url)
            val clientes: List<Cliente> = parserJson(json)

            for (c in clientes) {
                if (dao.getById(c.id!!) == null) {
                    dao.insert(c)
                }
            }

            return clientes
        }
        else {
            return dao.findAll()
        }
    }

    fun save(cliente: Cliente): Response {
        if (AndroidUtils.isInternetDisponivel(RMJSApplication.getInstance().applicationContext)) {
            var json = HttpHelper.post(
                "$host/clientes",
                cliente.toJson()
            )

            return parserJson(json)
        }
        else {
            if (dao.getById(cliente.id!!) == null) {
                dao.insert(cliente)
            }

            return Response(status = "OK", msg = "Cliente Inserido com Sucesso Localmente")
        }
    }

    fun delete(cliente: Cliente): Response {
        if (AndroidUtils.isInternetDisponivel(RMJSApplication.getInstance().applicationContext)) {
            val url = "$host/clientes/${cliente.id}"
            val json = HttpHelper.delete(url)
            dao.delete(cliente)

            return parserJson(json)
        }
        else {
            dao.delete(cliente)
            return Response(status = "OK", msg = "Cliente removido com sucesso localmente")
        }
    }

    inline fun <reified T> parserJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}