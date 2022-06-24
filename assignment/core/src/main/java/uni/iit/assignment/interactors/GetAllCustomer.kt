package uni.iit.assignment.interactors

import uni.iit.assignment.data.CustomerRepository

class GetAllCustomer(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke() = customerRepository.fetchAllCustomer()
}