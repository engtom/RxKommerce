package br.com.wale.getwater.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.wale.getwater.R
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.presenter.LoginPresenter
import br.com.wale.getwater.util.showToast
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Admin on 28/07/17.
 */

class LoginActivity : BaseActivity(), AppContract.ILoginView{

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)

        btnLogin.setOnClickListener{

            edtLogin?.let { login ->
                edtPassword?.let { password ->

                    presenter.login(login.text.toString(), password.text.toString())

                }
            }

        }

        btnSignUp.setOnClickListener{

            intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)

        }

    }

    override fun loginSucceeded() {

        val intent = Intent(this@LoginActivity, StoresActivity::class.java)
        startActivity(intent)

    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun getContext(): Context {
        return this@LoginActivity
    }

}
