package co.xware_tech.firebasecrud.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.xware_tech.firebasecrud.StudentData
import co.xware_tech.firebasecrud.ViewState
import co.xware_tech.firebasecrud.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val repository=Repository()
    private val _progressStateLive = MutableLiveData<ViewState<List<StudentData>>>()
    val progressStateLive: LiveData<ViewState<List<StudentData>>>
        get() = _progressStateLive
    fun getName(){
        viewModelScope.launch {
            _progressStateLive.value=ViewState.Loading
            repository.getName().fold({
                   _progressStateLive.value=ViewState.Error(it)
            },{
                _progressStateLive.value=ViewState.Success(it)
            })
        }
    }
    fun deleteName(id:String){
        repository.deleteName(id)
        getName()
    }
}