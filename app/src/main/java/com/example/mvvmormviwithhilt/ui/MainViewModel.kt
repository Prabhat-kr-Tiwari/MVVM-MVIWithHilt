package com.example.mvvmormviwithhilt.ui

//import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmormviwithhilt.model.Blog
import com.example.mvvmormviwithhilt.repository.MainRepository
import com.example.mvvmormviwithhilt.room.BlogDao
import com.example.mvvmormviwithhilt.util.DataState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject

//@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
     private val savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _dataState:MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState:LiveData<DataState<List<Blog>>>
        get() = _dataState


    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvents->{
                    mainRepository.getBlog()
                        .onEach {dataState->
                            _dataState.value=dataState

                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None->{
                    //who cares
                }
            }
        }
    }

}

sealed class MainStateEvent{
    object GetBlogEvents:MainStateEvent()
    object None:MainStateEvent()
}