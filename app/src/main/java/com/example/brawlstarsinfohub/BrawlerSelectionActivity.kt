package com.example.brawlstarsinfohub

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.brawlstarsinfohub.R.layout.activity_brawler_selection
import com.example.brawlstarsinfohub.databinding.ActivityBrawlerSelectionBinding

class BrawlerSelectionActivity : AppCompatActivity() {
    lateinit var bindingSelection:ActivityBrawlerSelectionBinding
     var flag: Boolean = false
    var fav : String = ""

    private val detailsActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {

                val favoritedBrawler = result.data?.getParcelableExtra<Brawler>("favorited_brawler")
                favoritedBrawler?.let {
                    fav = it.name.toString()
                    bindingSelection.tvFavBrawler.text= "Favorited ${it.name}"
                    Toast.makeText(this, "You favorited '${it.name}'. Look for more brawlers!", Toast.LENGTH_SHORT).show()                }
            } else if (result.resultCode == RESULT_CANCELED) {

                bindingSelection.tvFavBrawler.text = "No brawler favorited"
                Toast.makeText(this, "No brawler favorited!", Toast.LENGTH_SHORT).show()
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSelection=ActivityBrawlerSelectionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(bindingSelection.root)


        val animationBlink = AnimationUtils.loadAnimation(this, R.anim.blink)
        bindingSelection.tvWelcomeHeader.startAnimation(animationBlink)

        BrawlerSys.prepareData()

        if (bindingSelection.brawlerSpinner.adapter == null) {
            val adapt = CustomSpinnerAdapter(this, BrawlerSys.brawlerList)
            bindingSelection.brawlerSpinner.adapter = adapt
        }




        bindingSelection.brawlerSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedView: View?,
                position: Int,
                id: Long
            ) {

                val favoritedBrawler = BrawlerSys.brawlerList[position]

                if(flag)
                    bindingSelection.tvFavBrawler.text = "Favorited Brawler : ${favoritedBrawler.name} "
                else
                    bindingSelection.tvFavBrawler.text = ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                bindingSelection.tvFavBrawler.text = "No brawler favorited"
            }
        })


        bindingSelection.viewDetailsBtn.setOnClickListener {
            val selectedPosition = bindingSelection.brawlerSpinner.selectedItemPosition
            val favoritedBrawler = BrawlerSys.brawlerList[selectedPosition]

            val intent = Intent(this, DetailsActivity::class.java)
            flag = true
            intent.putExtra("brawler", favoritedBrawler)
            detailsActivityLauncher.launch(intent)
        }

        bindingSelection.goBackBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("brawlerName", fav)
            setResult(RESULT_OK, intent)
            finish()
        }
        }
}