package com.Addavi.addaviflow.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.Addavi.addaviflow.data.DataInstance
import com.Addavi.addaviflow.data.DataModel
import com.Addavi.addaviflow.data.DataModelRoot
import com.Addavi.addaviflow.data.uidata.ArzUiModel
import com.Addavi.addaviflow.data.uidata.UiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


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
                        "crypto-tether-irr"
                    )


                    val list = onSelectedKey.mapNotNull { key->
                        response[key]?.let { dataModel ->
                            ArzUiModel(
                                type = UiData.fromKey(key) ?: return@let null,
                                price = dataModel.p,
                                date = dataModel.d,
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