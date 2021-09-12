package co.xware_tech.firebasecrud.edit

import androidx.lifecycle.ViewModel
import co.xware_tech.firebasecrud.repository.Repository

class EditViewModel: ViewModel() {
    private val repository=Repository()
    fun updateName(id:String,updatedName:String){
        repository.updateName(id,updatedName)
    }
}