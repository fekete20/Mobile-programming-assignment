package uni.iit.assignment.interactors

import uni.iit.assignment.data.EmployeeRepository

class GetEmployeeById (private val employeeRepository: EmployeeRepository) {
    suspend operator fun invoke(id: Int) = employeeRepository.fetchEmployeeById(id)
}