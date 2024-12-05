package com.example.brawlstarsinfohub

object BrawlerSys {
val brawlerList = ArrayList<Brawler>()

    fun prepareData() {
        // Adding brawlers to the list with their attributes: name, Rarity, health, and damage
        brawlerList.add(Brawler("Mortis", "Epic", 2200, 500,R.drawable.mortis))
        brawlerList.add(Brawler("Gale", "Epic", 3500, 600,R.drawable.gale))
        brawlerList.add(Brawler("El Primo", "Rare", 7000, 800,R.drawable.elprimo))
        brawlerList.add(Brawler("Surge", "Legendary", 4000, 650,R.drawable.surge))
        brawlerList.add(Brawler("Colt", "Rare", 2800, 400,R.drawable.colt))
    }



}