package com.Addavi.addaviflow.data.uidata

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.Addavi.addaviflow.R

enum class UiData(
    val key: String,
    @StringRes val title: Int,
    @StringRes val fulltitle: Int,
    @DrawableRes val icon : Int
){

    EUR("price_eur" , R.string.euro_title , R.string.euro_full ,  R.drawable.euro_ico),
    TRY("price_try" , R.string.turkish_title , R.string.turkish_full , R.drawable.turkey_ico),
    AED("price_aed" , R.string.emirati_title , R.string.emirati_full , R.drawable.uae_ico),
    CAD("price_cad" , R.string.canada_title , R.string.canada_full , R.drawable.canada_ico),
    GPB("price_gbp" , R.string.england_title , R.string.england_full, R.drawable.uk_ico),
    GRAM("geram18" , R.string.gram_title , R.string.gram_full , R.drawable.gold_ico),
    EMM("sekee" , R.string.emami_title , R.string.emami_full , R.drawable.coin_ico),
    STB("sekeb" , R.string.azadi_title , R.string.azadi_full , R.drawable.coin_ico),
    NIM("nim" , R.string.nim_title , R.string.nim_full , R.drawable.coin_ico),
    BTC("crypto-bitcoin-irr" , R.string.btc_title , R.string.btc_full , R.drawable.bitc_ico),
    UST("crypto-tether-irr" , R.string.tether_title , R.string.tether_full , R.drawable.usdt_ico),
    USD("price_dollar_rl" , R.string.dollar_title , R.string.dollar_full , R.drawable.usa_ico);

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