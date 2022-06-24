package uni.iit.assignment.interactors

import uni.iit.assignment.data.CustomerRepository
import uni.iit.assignment.domain.Customer

class RemoveCustomer (private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) = customerRepository.removeCustomer(customer)
}