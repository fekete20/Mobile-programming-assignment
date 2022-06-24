package uni.iit.assignment.db.mapper

import uni.iit.assignment.db.entity.CustomerEntity
import uni.iit.assignment.domain.Customer

class CustomerMapper {
    fun mapToEntity(data: Customer) : CustomerEntity = CustomerEntity(
        data.id,
        data.name,
        data.location,
        data.comment
    )

    fun mapFromEntity(entity: CustomerEntity) = Customer(
        entity.id,
        entity.name,
        entity.location,
        entity.comment
    )
}