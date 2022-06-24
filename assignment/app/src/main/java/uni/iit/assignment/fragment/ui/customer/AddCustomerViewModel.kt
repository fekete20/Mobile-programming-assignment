package uni.iit.assignment.fragment.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uni.iit.assignment.domain.Customer
import uni.iit.assignment.interactors.AddCustomer

class AddCustomerViewModel(private val addCustomer: AddCustomer) : ViewModel() {
    private val _result = MutableLiveData<Boolean?>().apply { value = null }
    val result : LiveData<Boolean?> = _result

    fun save(customer: Customer) {
        viewModelScope.launch {
            try {
                addCustomer(customer)
                _result.postValue(true)

            } catch (err: Error) {
                _result.postValue(false)
            }

        }
    }
}