package somiah.jad.weekfirebaseapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore


class UserDataActivity : AppCompatActivity() {
    lateinit var title: EditText
    lateinit var detail: EditText
    lateinit var addBtn: Button
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)

        readFireStore()

        title = findViewById(R.id.title)
        detail = findViewById(R.id.detail)
        addBtn = findViewById(R.id.add_btn)

        addBtn.setOnClickListener {
            addToFireStore()
        }
    }

    fun readFireStore(){
        db = FirebaseFirestore.getInstance()
        db.collection("newsCollection")
            .get()
            .addOnCompleteListener {
                for(doc in it.result!! ){
                    Log.d("dataFromServer",doc.data.getValue("title").toString())
                }
            }
    }

    fun addToFireStore(){
        db = FirebaseFirestore.getInstance()
//        val row: MutableMap<String, Any> = HashMap()
//        row["title"] = "test from app"
//        row["details"]= "this is detail to the title from app"

        var t = title.text.toString()
        var d = detail.text.toString()

        val news = NewsCollection(t,d)
        db.collection("newsCollection")
            .add(news)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"data add successful", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"data add failed", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this,"can not add data ${e.message }", Toast.LENGTH_LONG).show() }

    }
}