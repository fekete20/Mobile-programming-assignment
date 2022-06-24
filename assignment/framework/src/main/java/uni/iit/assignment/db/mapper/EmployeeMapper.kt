package uni.iit.assignment.db.mapper

import uni.iit.assignment.db.entity.EmployeeEntity
import uni.iit.assignment.domain.Employee

class EmployeeMapper {
    fun mapToEntity(data: Employee) : EmployeeEntity = EmployeeEntity(
        data.id,
        data.name,
        data.position,
        data.location,
        data.salary
    )

    fun mapFromEntity(entity: EmployeeEntity) = Employee (
        entity.id,
        entity.name,
        entity.position,
        entity.location,
        entity.salary
    )
}