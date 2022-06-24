package uni.iit.assignment.fragment.ui.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uni.iit.assignment.domain.Customer
import uni.iit.assignment.interactors.GetAllCustomer
import uni.iit.assignment.interactors.RemoveCustomer
import uni.iit.assignment.interactors.UpdateCustomer
import java.lang.Error

class CustomerViewModel(private val getAllCustomer: GetAllCustomer, private val removeCustomer: RemoveCustomer, private val updateCustomer: UpdateCustomer) : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>().apply {
        value = emptyList()
    }

    private val _deletedCustomer = MutableLiveData<Customer>()
    private val _updatedCustomer = MutableLiveData<Customer>()

    val customers : LiveData<List<Customer>> = _customers
    val deletedCustomer : LiveData<Customer> = _deletedCustomer
    val updatedCustomer : LiveData<Customer> = _updatedCustomer

    fun loadCustomers() {
        viewModelScope.launch {
            _customers.postValue(getAllCustomer())
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            try {
                removeCustomer(customer)
                _deletedCustomer.postValue(customer)

            } catch (err: Error)
            {
                error(err)
            }

        }
    }

    fun editCustomer(customer: Customer) {
        viewModelScope.launch {
            try {
                updateCustomer(customer)
                _updatedCustomer.postValue(customer)
            } catch (err : Error) {
                error(err)
            }
        }
    }
}