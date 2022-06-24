package uni.iit.assignment.interactors

import uni.iit.assignment.data.CustomerRepository

class GetCustomerById (private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(id: Int) = customerRepository.fetchCustomerById(id)
}