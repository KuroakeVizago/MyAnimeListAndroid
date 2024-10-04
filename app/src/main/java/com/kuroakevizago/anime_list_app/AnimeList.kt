package com.kuroakevizago.anime_list_app
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuroakevizago.anime_list_app.adapter.AnimeListAdapter
import com.kuroakevizago.anime_list_app.data.AnimeData
import com.kuroakevizago.anime_list_app.databinding.ActivityAnimeListBinding
import com.kuroakevizago.anime_list_app.interfaces.OnItemClickListener


class AnimeList : AppCompatActivity(), OnItemClickListener {

    private lateinit var animeListBinding : ActivityAnimeListBinding
    private var animeList = ArrayList<AnimeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animeListBinding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(animeListBinding.root)

        animeListBinding.rvAnimeList.setHasFixedSize(true)

        animeList.addAll(getAllAnime())
        showRecycleList()

        customToolbarSetup()
    }

    private fun customToolbarSetup() {
        // Set up the custom toolbar
        setSupportActionBar(animeListBinding.toolbar.root)

        // Handle profile button click
        val profileButton: ImageView = animeListBinding.toolbar.profileButton
        profileButton.setOnClickListener {
            profileButtonAction()
        }
    }

    private fun profileButtonAction() {
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }

    private fun getAllAnime() : ArrayList<AnimeData> {
        val dataName = resources.getStringArray(R.array.anime_names)
        val dataDescription = resources.getStringArray(R.array.anime_description)
        val dataImage = resources.getStringArray(R.array.anime_images)
        val dataRating = resources.getStringArray(R.array.anime_rating).mapNotNull { it.toFloatOrNull() }.toFloatArray()
        val dataGenres = resources.getStringArray(R.array.anime_genres)

        val animeList = ArrayList<AnimeData>()

        for (i in dataName.indices) {
         animeList.add(
             AnimeData(dataName[i], dataDescription[i], dataImage[i], dataGenres[i], dataRating[i])
         )
        }

        return animeList
    }

    private fun showRecycleList() {
        animeListBinding.rvAnimeList.layoutManager = LinearLayoutManager(this)
        val animeListAdapter = AnimeListAdapter(animeList, this)
        animeListBinding.rvAnimeList.adapter = animeListAdapter

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, AnimeDetail::class.java)
        intent.putExtra(
            "anime_detail",
            animeList[position]
            )
        startActivity(intent)
    }
}