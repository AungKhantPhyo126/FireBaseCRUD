package co.xware_tech.firebasecrud.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.xware_tech.firebasecrud.repository.Repository
import kotlinx.coroutines.launch

class CreateViewModel:ViewModel() {
    private val repository=Repository()
    fun addName(name:String){
        viewModelScope.launch {
            repository.addStudentName(name)
        }
    }
}