package uni.iit.assignment.fragment.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uni.iit.assignment.domain.Employee
import uni.iit.assignment.interactors.AddEmployee

class AddEmployeeViewModel(private val addEmployee: AddEmployee) : ViewModel() {
    private val _result = MutableLiveData<Boolean?>().apply { value = null }
    val result : LiveData<Boolean?> = _result

    fun save(employee: Employee) {
        viewModelScope.launch {
            try {
                addEmployee(employee)
                _result.postValue(true)

            } catch (err: Error) {
                _result.postValue(false)
            }

        }
    }
}