package somiah.jad.weekfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var userEmail: EditText
    lateinit var userPass: EditText
    lateinit var loginBtn: Button
    lateinit var signUptn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userEmail = findViewById(R.id.user_email)
        userPass = findViewById(R.id.user_pass)
        loginBtn = findViewById(R.id.log_in)
        signUptn = findViewById(R.id.sign_up)

        signUptn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}