package com.example.brawlstarsinfohub

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.brawlstarsinfohub.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
lateinit var bindingMain:ActivityMainBinding
lateinit var resultLauncher:ActivityResultLauncher<Intent>
var flag:Boolean=false
private var resultFav:Brawler?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingMain=ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        bindingMain.brawlerListBtn.setOnClickListener {
            val intentMain = Intent(this, BrawlerSelectionActivity::class.java)
            startActivity(intentMain)

        }
//        resultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
//            if (result.resultCode==RESULT_OK){
//                resultFav=result.data?.getParcelableExtra<Brawler>("brawlerName")
//                resultFav?.let{
//                    Toast.makeText(this, "Your Fav is ${it.name}", Toast.LENGTH_LONG).show()
//                }
//            }
//        }

        bindingMain.selectedBrawlersBtn.setOnClickListener {
            val favoritedBrawler = intent.getParcelableExtra<Brawler>("favorited_brawler")
            if (favoritedBrawler == null) {
                Snackbar.make(bindingMain.root, "You need to choose a brawler first!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(R.color.orange))  //
                    .show()
            } else {
                val intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
            }
        }


    }


}
