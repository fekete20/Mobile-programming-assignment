package uni.iit.assignment.data

import uni.iit.assignment.domain.Customer

interface CustomerDataSource {
    suspend fun addCustomer(customer: Customer)
    suspend fun removeCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
    suspend fun fetchAllCustomer() : List<Customer>
    suspend fun fetchCustomerById(id: Int) : Customer?

}