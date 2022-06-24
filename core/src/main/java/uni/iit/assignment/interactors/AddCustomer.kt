package uni.iit.assignment.interactors

import uni.iit.assignment.data.CustomerRepository
import uni.iit.assignment.domain.Customer

class AddCustomer(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) = customerRepository.addCustomer(customer)
}