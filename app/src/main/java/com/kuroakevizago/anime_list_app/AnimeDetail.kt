package com.kuroakevizago.anime_list_app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.kuroakevizago.anime_list_app.data.AnimeData
import com.kuroakevizago.anime_list_app.databinding.ActivityAnimeDetailBinding

@Suppress("DEPRECATION")
class AnimeDetail : AppCompatActivity() {

    private lateinit var binding : ActivityAnimeDetailBinding
    private var animeData: AnimeData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = this.intent
        animeData = intent.getParcelableExtra("anime_detail")
        animeData?.let {
            Glide.with(binding.root).load(it.imageUrl).into(binding.imgAnimePoster)
            binding.tvAnimeTitle.text = it.name
            binding.tvAnimeDescription.text = it.description
            binding.tvAnimeGenres.text = it.genres
            binding.rbAnimeRating.rating = it.rating
            binding.tvAnimeRatingValue.text = buildString {
                append(it.rating.toString())
                append("/5")
            }
        }

        binding.actionShare.setOnClickListener {
            shareAction()
        }

        customToolbarSetup()
    }

    private fun customToolbarSetup() {
        // Set up the custom toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Handle profile button click
//        val profileButton: ImageView = binding.toolbar.profileButton
//        profileButton.setOnClickListener {
//            profileButtonAction()
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun profileButtonAction() {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }

    private fun shareAction() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                "Hey check this anime out!" +
                        "\n\nTitle: ${animeData?.name}" +
                        "\n\nGenres: ${animeData?.genres}" +
                        "\n\nSynopsis: ${animeData?.description}")
            type = "text/plain"  // Specify the type
        }

        val chooser = Intent.createChooser(shareIntent, "Share via")
        startActivity(chooser)
    }
}