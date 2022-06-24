package uni.iit.assignment.data

import uni.iit.assignment.domain.Employee

interface EmployeeDataSource {
    suspend fun addEmployee(employee: Employee)
    suspend fun removeEmployee(employee: Employee)
    suspend fun updateEmployee(employee: Employee)
    suspend fun fetchAllEmployee() : List<Employee>
    suspend fun fetchEmployeeById(id: Int) : Employee?

}