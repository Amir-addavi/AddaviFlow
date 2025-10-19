package com.Addavi.addaviflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Addavi.addaviflow.data.ArzModel
import com.Addavi.addaviflow.data.remote.ArzService
import com.Addavi.addaviflow.data.remote.repository.ArzRepository
import com.Addavi.addaviflow.data.remote.repository.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArzViewModel(
    private val repository: ArzRepository = ArzRepository()
): ViewModel(){
    private val _State = MutableStateFlow<Resource<List<ArzModel>>>(Resource.Loading)
    val state: StateFlow<Resource<List<ArzModel>>> = _State.asStateFlow()

    private val targetIds = listOf(0, 1, 3, 17 , 21 , 2)

    init {
        refresh()
    }
    fun refresh(){
        viewModelScope.launch {
            _State.value = Resource.Loading
            val result = repository.fetchArzList()
            _State.value = when (result){
                is Resource.Succes -> {
                    val filteredList = result.data.filter { it.id in targetIds }.sortedBy { arzModel -> targetIds.indexOf(arzModel.id) }
                    Resource.Succes(filteredList)
                }else -> result
            }
        }
    }
}