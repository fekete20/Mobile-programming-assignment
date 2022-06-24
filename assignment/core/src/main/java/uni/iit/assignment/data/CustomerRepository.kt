package uni.iit.assignment.data

import uni.iit.assignment.domain.Customer

class CustomerRepository(private val dataSource: CustomerDataSource) {
    suspend fun addCustomer(customer: Customer) = dataSource.addCustomer(customer)
    suspend fun removeCustomer(customer: Customer) = dataSource.removeCustomer(customer)
    suspend fun updateCustomer(customer: Customer) = dataSource.updateCustomer(customer)
    suspend fun fetchAllCustomer() = dataSource.fetchAllCustomer()
    suspend fun fetchCustomerById(id: Int) = dataSource.fetchCustomerById(id)

}