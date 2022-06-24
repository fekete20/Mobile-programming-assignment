package uni.iit.assignment.db.dao

import androidx.room.*
import uni.iit.assignment.db.entity.CustomerEntity

@Dao
interface CustomerDao {
    @Insert
    fun insert(entity: CustomerEntity)

    @Update
    fun update(entity: CustomerEntity)

    @Delete
    fun delete(entity: CustomerEntity)

    @Query("select * from customer")
    fun fetchAll(): List<CustomerEntity>

    @Query("select count(*) from customer")
    fun fetchCountCustomers() : Int?

    @Query("select * from customer where id = :id")
    fun fetchCustomerById(id: Int): CustomerEntity?
}