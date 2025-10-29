package com.Addavi.addaviflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Addavi.addaviflow.data.ApiInterface
import com.Addavi.addaviflow.data.DataInstance
import com.Addavi.addaviflow.data.uidata.ArzUiModel
import com.Addavi.addaviflow.data.uidata.UiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.collections.component1
import kotlin.collections.component2


sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}


class  FetchDataViewModel : ViewModel(){
private val _allItem = MutableStateFlow<Resource<List<ArzUiModel>>>(Resource.Loading)
val allItem : StateFlow<Resource<List<ArzUiModel>>> = _allItem.asStateFlow()


    init {
        FetchData()
    }


    private fun formatPriceWithUnit(priceRial: String): String {
        val clean = priceRial.replace(",", "").trim()
        if (clean.isEmpty() || clean.all { it !in '0'..'9' }) return "نامعتبر"
        if (clean.length <= 1) return clean

        val tomanStr = clean.dropLast(1)
        val digitCount = tomanStr.length

        return try {
            val number = tomanStr.toLong()
            val formattedNumber = String.format("%,d", number)

            when {
                digitCount >= 10 -> "${formattedNumber}B"
                digitCount in 7..9-> "${formattedNumber}M"
                digitCount in 4..6 -> "${formattedNumber}K"
                else -> "${formattedNumber}R"
            }
        } catch (e: NumberFormatException) {
            tomanStr
        }
    }


    fun FetchData(){
        viewModelScope.launch {
            try {
                _allItem.value = Resource.Loading
                val response = DataInstance.apiInterface.getdata().current

                    val onSelectedKey = setOf(
                        "price_dollar_rl",
                        "price_eur",
                        "price_gbp",
                        "price_cad",
                        "price_try",
                        "price_aed",
                        "geram18",
                        "sekee",
                        "sekeb",
                        "nim",
                        "crypto-bitcoin-irr",
                        "crypto-tether-irr",
                        "crypto-ethereum-irr",
                        "crypto-solana-irr",
                        "crypto-dogecoin-irr",
                        "crypto-ripple-irr"
                    )


                    val list = onSelectedKey.mapNotNull { key->
                        response[key]?.let { dataModel ->
                            ArzUiModel(
                                type = UiData.fromKey(key) ?: return@let null,
                                price = formatPriceWithUnit(dataModel.p),
                                date = formatPriceWithUnit(dataModel.d),
                                dt = dataModel.dt
                            )
                        }
                 }

                if (list.isEmpty()) {
                    _allItem.value = Resource.Error("داده‌ای برای نمایش وجود ندارد")
                } else {
                    _allItem.value = Resource.Success(list)
                }
            }catch (e: IOException) {
                _allItem.value = Resource.Error(e.message ?: "اتصال اینترنت برقرار نیست")
            }
            catch (e: Exception) {
                _allItem.value = Resource.Error("خطای غیرمنتظره رخ داده است")
            }
        }
    }
}

