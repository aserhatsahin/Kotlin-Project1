package com.example.brawlstarsinfohub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.brawlstarsinfohub.BrawlerSys.brawlerList

class CustomSpinnerAdapter(
    context: Context,
    private val concertList: List<Brawler>
) : ArrayAdapter<Brawler>(context, R.layout.spinner_item, concertList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createCustomView(position, convertView, parent)
    }

    private fun createCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)

        val brawler = brawlerList[position]

        val thumbnail = view.findViewById<ImageView>(R.id.brawlerThumbnail)
        val name = view.findViewById<TextView>(R.id.tvBrawlerName)



        // Resmi dinamik olarak ayarlama
        val imageResId = when (brawler.name) {
            "El Primo" -> R.drawable.elprimo
            "Colt" -> R.drawable.colt
            "Surge" -> R.drawable.surge
            "Gale" -> R.drawable.gale
            "Mortis" -> R.drawable.mortis
            else -> R.drawable.ic_launcher_foreground // Default image
        }


        thumbnail.setImageResource(imageResId)

        // Diğer verileri ayarlama
        name.text = brawler.name


        return view
    }
}


