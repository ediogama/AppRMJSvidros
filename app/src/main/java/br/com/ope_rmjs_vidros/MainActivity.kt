package br.com.ope_rmjs_vidros

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

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

        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)
        var checkBox = findViewById<CheckBox>(R.id.checkBox)

        campoUsuario.setText(Prefs.getString("lembrarUsuario"))
        campoSenha.setText(Prefs.getString("lembrarSenha"))
        checkBox.isChecked = Prefs.getBoolean("checkLembrar")
    }

    private fun OnClickLogin() {

        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)

        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()


        if (campoUsuario.text.toString().equals("aluno") && campoSenha.text.toString().equals("impacta")){
            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario ou Senha incorretos!", Toast.LENGTH_SHORT).show()
        }

        if (checkBox.isChecked){
            Prefs.setString("lembrarUsuario", valorUsuario)
            Prefs.setString("lembrarSenha", valorSenha)
            Prefs.setBoolean("checkLembrar", checkBox.isChecked)
        }
        else {
            Prefs.setString("lembrarUsuario", "")
            Prefs.setString("lembrarSenha", "")
        }
    }

}
