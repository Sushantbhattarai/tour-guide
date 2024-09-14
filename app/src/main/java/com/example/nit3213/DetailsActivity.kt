package com.example.nit3213
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nit3213.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val destination = intent.getParcelableExtra<Destination>("destination")

        findViewById<TextView>(R.id.destinationName).text = destination?.destination
        findViewById<TextView>(R.id.country).text = destination?.country
        findViewById<TextView>(R.id.bestSeason).text = "Best season: ${destination?.bestSeason}"
        findViewById<TextView>(R.id.popularAttraction).text = "Attraction: ${destination?.popularAttraction}"
        findViewById<TextView>(R.id.description).text = destination?.description
    }
}

