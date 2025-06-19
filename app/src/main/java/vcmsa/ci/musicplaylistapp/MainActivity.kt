package vcmsa.ci.musicplaylistapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private fun Intent.putStringArrayListExtra(s: String, ratings: java.util.ArrayList<Int>) {

}

class MainActivity : AppCompatActivity() {


    companion object {
        val songTitles = ArrayList<String>()
        val artistNames = ArrayList<String>()
        val Ratings = ArrayList<Int>()
        val Comments = ArrayList<String>()
    }

    var btnAdd: Button? = null
    var btnNext: Button? = null
    var btnExit: Button? = null

    private fun <T> findViewById(btnAdd: Button?): View? {


        fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

            val songTitleInput = findViewById<View>(R.id.songTitleInput) as EditText
            val artistNameInput = findViewById<View>(R.id.artistNameInput) as EditText
            val ratingInput = findViewById<View>(R.id.ratingInput) as EditText
            val commentsInput = findViewById<View>(R.id.commentsInput) as EditText

            btnAdd = findViewById<View>(btnAdd) as Button
            btnNext = findViewById<View>(R.id.btnNext) as Button
            btnExit = findViewById<View>(R.id.btnExit) as Button

            btnAdd!!.setOnClickListener {
                val songTitles = songTitleInput.text.toString()
                val artistNames = artistNameInput.text.toString()
                val Ratings = ratingInput.text.toString().toIntOrNull()
                val Comments = commentsInput.text.toString()

                if (songTitles.isBlank() || artistNames.isBlank() || Ratings.== { null == true || Ratings !in 1..5 }) {
                Toast.makeText(this, "Enter valid inputs (Rating 1-5)", Toast.LENGTH_SHORT).show()

            }
                Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
                //Clear inputs
                songTitleInput.text.clear()
                artistNameInput.text.clear()
                ratingInput.text.clear()
                commentsInput.text.clear()
            }

            btnNext!!.setOnClickListener {
                val intent = Intent(this, detailedViewScreen::class.java)
                intent.putStringArrayListExtra("Titles", songTitles)
                intent.putStringArrayListExtra("artists", artistNames)
                intent.putStringArrayListExtra("ratings", Ratings)
                intent.putStringArrayListExtra("comments", Comments)
                startActivity(intent)
            }
            btnExit!!.setOnClickListener {
                finishAffinity()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }}


