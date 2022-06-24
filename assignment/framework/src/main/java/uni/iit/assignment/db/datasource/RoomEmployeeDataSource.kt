package uni.iit.assignment.db.datasource

import uni.iit.assignment.data.EmployeeDataSource
import uni.iit.assignment.db.dao.EmployeeDao
import uni.iit.assignment.db.mapper.EmployeeMapper
import uni.iit.assignment.domain.Employee

class RoomEmployeeDataSource(private val dao: EmployeeDao, private val mapper: EmployeeMapper) : EmployeeDataSource {
    override suspend fun addEmployee(employee: Employee) = dao.insert(mapper.mapToEntity(employee))
    override suspend fun fetchAllEmployee(): List<Employee> = dao.fetchAll().map { mapper.mapFromEntity(it) }
    override suspend fun updateEmployee(employee: Employee) = dao.update(mapper.mapToEntity(employee))
    override suspend fun removeEmployee(employee: Employee) = dao.delete(mapper.mapToEntity(employee))
    override suspend fun fetchEmployeeById(id: Int): Employee? = dao.fetchEmployeeById(id)?.let { mapper.mapFromEntity(it) }


}

