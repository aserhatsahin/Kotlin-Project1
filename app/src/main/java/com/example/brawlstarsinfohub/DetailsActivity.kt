package com.example.brawlstarsinfohub

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.brawlstarsinfohub.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var bindingDetails: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingDetails=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(bindingDetails.root)

        val brawler = intent.getParcelableExtra<Brawler>("brawler")
       brawler?.let {
            bindingDetails.imgBrawler.setImageResource(it.imageId)
            bindingDetails.brawlerName.text = "${it.name}"
            bindingDetails.brawlerRarityValue.text = "${it.rarity}"
            bindingDetails.brawlerHealthValue.text = "${it.health}"
            bindingDetails.brawlerDamageValue.text = "${it.damage}"


        }

        val width =  bindingDetails.imgBrawler.layoutParams.width
        val height = bindingDetails.imgBrawler.layoutParams.height

        bindingDetails.ratingSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar?.let {
                bindingDetails.imgBrawler.layoutParams.height = height + progress
                bindingDetails.imgBrawler.layoutParams.width = width + progress
                bindingDetails.imgBrawler.requestLayout()
            }}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        bindingDetails.favoriteBtn.setOnClickListener {
            showFavDialog(brawler)
        }
        bindingDetails.goHomeBtn.setOnClickListener {
            Toast.makeText(this, "Action canceled!", Toast.LENGTH_SHORT).show()
            setResult(RESULT_CANCELED)
            finish()
        }

    }
    private fun showFavDialog(brawler: Brawler?) {
        if (brawler == null) return

        AlertDialog.Builder(this)
            .setTitle("Confirm Favorite")
            .setMessage("Do you want to favorite this brawler?\n\nName: ${brawler.name}\nRarity: ${brawler.rarity}\nHealth:  ${brawler.health} \nDamage:  ${brawler.damage}")
            .setPositiveButton("Yes") { _, _ ->
                val resultIntent = Intent()
                resultIntent.putExtra("favorited_brawler", brawler)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    }
