package uni.iit.assignment.db.datasource

import uni.iit.assignment.data.CustomerDataSource
import uni.iit.assignment.db.dao.CustomerDao
import uni.iit.assignment.db.mapper.CustomerMapper
import uni.iit.assignment.domain.Customer

class RoomCustomerDataSource (private val dao: CustomerDao, private val mapper: CustomerMapper) : CustomerDataSource {
    override suspend fun addCustomer(customer: Customer) = dao.insert(mapper.mapToEntity(customer))
    override suspend fun fetchAllCustomer() : List<Customer> = dao.fetchAll().map { mapper.mapFromEntity(it) }
    override suspend fun updateCustomer(customer: Customer) = dao.update(mapper.mapToEntity(customer))
    override suspend fun removeCustomer(customer: Customer) = dao.delete(mapper.mapToEntity(customer))
    override suspend fun fetchCustomerById(id: Int): Customer? = dao.fetchCustomerById(id)?.let { mapper.mapFromEntity(it)}

}