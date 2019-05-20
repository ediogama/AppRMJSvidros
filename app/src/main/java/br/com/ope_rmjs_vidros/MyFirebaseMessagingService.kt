package br.com.ope_rmjs_vidros

import android.content.Intent
import android.util.Log
import br.com.ope_rmjs_vidros.modelo.Cliente
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService(){
    val TAG = "RMJSFirebase"

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG, "NOVO TOKEN: $token")

        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {

        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            var corpo = mensagemRemota?.notification?.body
            Log.d(TAG, "Titulo: $titulo")
            Log.d(TAG, "Corpo: $corpo")

            if (mensagemRemota?.data.isNotEmpty()) {
                val clienteId = mensagemRemota?.data.get("clienteId")
                Log.d(TAG, "Dados: $clienteId")
            }
            showNotification(mensagemRemota!!)
        }
    }

    private fun showNotification(mensagemRemota: RemoteMessage) {
        val intent = Intent(this, ClienteActivity::class.java)
        intent.putExtra("cliente", Cliente())
        val titulo = mensagemRemota?.notification?.title
        val corpo = mensagemRemota?.notification?.body
        NotificationUtil.create(1, intent, titulo!!, corpo!!)
    }
}