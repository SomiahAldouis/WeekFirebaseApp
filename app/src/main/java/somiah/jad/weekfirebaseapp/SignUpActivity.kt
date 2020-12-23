package somiah.jad.weekfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    lateinit var userEmail: EditText
    lateinit var userPass: EditText
    lateinit var signUptn: Button
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        userEmail = findViewById(R.id.email)
        userPass = findViewById(R.id.pass)
        signUptn = findViewById(R.id.signup)

        signUptn.setOnClickListener {
            registration()
        }

    }

    fun registration(){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(
            userEmail.text.toString(),
            userPass.text.toString())
            .addOnCompleteListener (this){
                if(it.isSuccessful){
                    Toast.makeText(this,"user Created Successful ", Toast.LENGTH_LONG).show()
                    val intent = Intent(this,UserDataActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Can not Create ", Toast.LENGTH_LONG).show()
                    Log.d("failed", it.exception.toString())
                }
            }
    }
}