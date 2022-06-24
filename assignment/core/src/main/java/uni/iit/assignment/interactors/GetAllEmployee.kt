package uni.iit.assignment.interactors

import uni.iit.assignment.data.EmployeeRepository

class GetAllEmployee(private val employeeRepository: EmployeeRepository) {
    suspend operator fun invoke() = employeeRepository.fetchAllEmployee()
}