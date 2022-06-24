package uni.iit.assignment.interactors

import uni.iit.assignment.data.EmployeeRepository
import uni.iit.assignment.domain.Employee

class UpdateEmployee (private val employeeRepository: EmployeeRepository) {
    suspend operator fun invoke(employee: Employee) = employeeRepository.updateEmployee(employee)
}