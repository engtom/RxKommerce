package br.com.wale.getwater.view.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import br.com.wale.getwater.R
import br.com.wale.getwater.contract.AppContract
import br.com.wale.getwater.presenter.SignUpPresenter
import br.com.wale.getwater.util.showToast
import kotlinx.android.synthetic.main.activity_sign_up.*

/**
 * Created by Admin on 28/07/17.
 */
class SignUpActivity : BaseActivity(), AppContract.ISignUpView {

    private lateinit var presenter: SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        presenter = SignUpPresenter(this)

        btnSignUp.setOnClickListener {
            presenter.addUser(edtName.text.toString(), edtEmail.text.toString(), edtPassword.text.toString(), edtConfirmPassword.text.toString())
        }

    }

    override fun signUpSucceeded() {

        AlertDialog.Builder(getContext())
                .setTitle("Sucesso")
                .setMessage("Cadastro realizado com sucesso")
                .setPositiveButton("OK", { p0, p1 ->

                    p0.dismiss()

                    val intent = Intent(getContext(), StoresActivity::class.java)
                    startActivity(intent)

                })
                .setCancelable(false)
                .show()




    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun getContext(): Context {
        return this@SignUpActivity
    }

}