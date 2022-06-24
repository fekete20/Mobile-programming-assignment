package uni.iit.assignment.data

import uni.iit.assignment.domain.Employee

class EmployeeRepository(private val dataSource: EmployeeDataSource) {
    suspend fun addEmployee(employee: Employee) = dataSource.addEmployee(employee)
    suspend fun removeEmployee(employee: Employee) = dataSource.removeEmployee(employee)
    suspend fun updateEmployee(employee: Employee) = dataSource.updateEmployee(employee)
    suspend fun fetchAllEmployee() = dataSource.fetchAllEmployee()
    suspend fun fetchEmployeeById(id: Int) = dataSource.fetchEmployeeById(id)

}