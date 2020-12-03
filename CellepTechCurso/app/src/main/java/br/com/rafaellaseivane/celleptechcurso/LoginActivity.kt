package br.com.rafaellaseivane.celleptechcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Executando (escutando) o clique do botão entrar
        btnLoginEntrar.setOnClickListener {

            //Capturando os dados digitados
            val email = edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()

            //Validação dos campos
            if(email.isEmpty()){
                edtLoginEmail.error = "Campo obrigatório!"
                edtLoginEmail.requestFocus()
            }else if(senha.isEmpty()){
                edtLoginSenha.error = "Campo obrigatório!"
                edtLoginSenha.requestFocus()

            }else{ // email e senha não são vazios
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                // Recuperando os dados do shared Preference
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                //Verificar o email e senha
                if(email == emailPrefs && senha == senhaPrefs) {
                    //apresentar uma mensagem de sucesso ao usuario
                    Toast.makeText(this, "Usuário logado com sucesso!", Toast.LENGTH_LONG).show()

                    //Abrindo a MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)
                    mIntent.putExtra("INTENT_EMAIL", email)

                    startActivity(mIntent)
                    finish()
                }else{
                    //Apresentando uma mensagem de erro ao usuário: desafio 2 minutos
                    Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show()
                }
            }
        }
        //Executando o clique do botão cadastrar:
        btnLoginCadastrar.setOnClickListener {
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        }
        
    }
}