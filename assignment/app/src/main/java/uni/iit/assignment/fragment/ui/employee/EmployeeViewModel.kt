package uni.iit.assignment.fragment.ui.employee

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uni.iit.assignment.domain.Employee
import uni.iit.assignment.interactors.GetAllEmployee
import uni.iit.assignment.interactors.RemoveEmployee
import uni.iit.assignment.interactors.UpdateEmployee
import java.lang.Error

class EmployeeViewModel(private val getAllEmployee: GetAllEmployee, private val removeEmployee: RemoveEmployee, private val updateEmployee: UpdateEmployee) : ViewModel() {

   private val _employees = MutableLiveData<List<Employee>>().apply {
       value = emptyList()
   }

    private val _deletedEmployee = MutableLiveData<Employee>()

    private val _updatedEmployee = MutableLiveData<Employee>()

    val employees : LiveData<List<Employee>> = _employees
    val deletedEmployee : LiveData<Employee> = _deletedEmployee
    val updatedEmployee : LiveData<Employee> = _updatedEmployee

    fun loadEmployees() {
        viewModelScope.launch {
            _employees.postValue(getAllEmployee())
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                removeEmployee(employee)
                _deletedEmployee.postValue(employee)

            } catch (err: Error)
            {
                error(err)
            }

        }
    }

    fun editEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                updateEmployee(employee)
                _updatedEmployee.postValue(employee)
            } catch (err : Error) {
                error(err)
            }
        }
    }
}