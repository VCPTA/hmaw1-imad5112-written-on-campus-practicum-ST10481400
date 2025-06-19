package vcmsa.ci.musicplaylistapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detailedViewScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)

        val textView= findViewById<View>(R.id.textView) as TextView
       val btnBack= findViewById<View>(R.id.btnBack) as Button
       val btnDisplay= findViewById<View>(R.id.btnDisplay) as Button
       val txtAverage= findViewById<View>(R.id.txtAverage) as TextView
        val btnAvg = findViewById<View>(R.id.btnAvg) as Button
        val displayText = findViewById<View>(R.id.displayText) as TextView

        //Get data from intent
        val songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        val ratings = intent.getStringArrayListExtra("ratings") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        btnDisplay.setOnClickListener {
            if (songs.isEmpty()) {
                displayText.text = "No songs in playlist."

            } else {
                val builder = StringBuilder()
                for (i in songs.indices) {
                    builder.append("Song: ${i + 1}\n")
                    builder.append("songs: ${songs[i]}\n")
                    builder.append("Artist: ${artists[i]}\n")
                    builder.append("Rating: ${ratings[i]}\n")
                    builder.append("Comments: ${comments}\n")
                }
                displayText.text = builder.toString()
            }
        }

        btnAvg.setOnClickListener {
            if (ratings.isEmpty()) {
                txtAverage.text = "Average Rating: N/A"
            } else {
                val average = ratings.sum() .toDouble() / ratings.size
                txtAverage.text = "Average Rating: %2f". format(average)
            }

        }

        btnBack!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}