package br.com.ope_rmjs_vidros

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.imagem_login)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_login)

        val botaoLogin = findViewById<Button>(R.id.botao_login)
        botaoLogin.setOnClickListener { OnClickLogin() }

    }

    private fun OnClickLogin() {
        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)
        if (campoUsuario.text.toString().equals("aluno") && campoSenha.text.toString().equals("impacta")){
            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario ou Senha incorretos!", Toast.LENGTH_SHORT).show()
        }
    }

}
