package uni.iit.assignment.interactors

import uni.iit.assignment.data.EmployeeRepository
import uni.iit.assignment.domain.Employee


class AddEmployee(private val employeeRepository: EmployeeRepository) {
    suspend operator fun invoke(employee: Employee) = employeeRepository.addEmployee(employee)
}