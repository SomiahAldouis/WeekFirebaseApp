package somiah.jad.weekfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var userEmail: EditText
    lateinit var userPass: EditText
    lateinit var loginBtn: Button
    lateinit var signUptn: Button
    lateinit var auth: FirebaseAuth

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

        loginBtn.setOnClickListener {
            Login()
        }
    }
    fun Login(){
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(
            userEmail.text.toString(),
            userPass.text.toString())
            .addOnCompleteListener (this){
                if(it.isSuccessful){
                    Toast.makeText(this,"user Login Successful ", Toast.LENGTH_LONG).show()
                    val intent = Intent(this,UserDataActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Can not Login ", Toast.LENGTH_LONG).show()
                    Log.d("failed", it.exception.toString())
                }
            }
    }
}