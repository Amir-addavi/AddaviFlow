package com.Addavi.addaviflow.data.uidata

import androidx.annotation.DrawableRes
import com.Addavi.addaviflow.R

enum class UiData(
    val key: String,
    val title : String,
    val fulltitle : String,
    @DrawableRes val icon : Int
){

    EUR("price_eur" , "EUR" , "Euro" , R.drawable.euro_ico),
    TRY("price_try" , "TRY" , "Turkish Lira" , R.drawable.turkey_ico),
    AED("price_aed" , "AED" , "Emirati Dirham" , R.drawable.uae_ico),
    CAD("price_cad" , "CAD" , "Canada Dollar" , R.drawable.canada_ico),
    GPB("price_gbp" , "GPB" , "UK Pound" , R.drawable.uk_ico),
    GRAM("geram18" , "GRAM" , "Gram" , R.drawable.gold_ico),
    EMM("sekee" , "EMM" , "Emami" , R.drawable.coin_ico),
    STB("sekeb" , "AZD" , "Azadi" , R.drawable.coin_ico),
    NIM("nim" , "NIM" , "Azadi 1/2" , R.drawable.coin_ico),
    BTC("crypto-bitcoin-irr" , "BTC" , "Bitcoin" , R.drawable.bitc_ico),
    UST("crypto-tether-irr" , "USDT" , "Tether" , R.drawable.usdt_ico),
    USD("price_dollar_rl" , "USD" , "US Dollar" , R.drawable.usa_ico);

    companion object{
        fun fromKey(key: String): UiData? = values().find { it.key == key }
    }
}

data class ArzUiModel(
    val type: UiData,
    val price : String,
    val date : String,
    val dt : String
)