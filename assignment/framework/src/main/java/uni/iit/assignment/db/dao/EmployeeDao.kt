package uni.iit.assignment.db.dao

import androidx.room.*
import uni.iit.assignment.db.entity.EmployeeEntity

@Dao
interface EmployeeDao {
    @Insert
     fun insert(entity: EmployeeEntity)

    @Update
     fun update(entity: EmployeeEntity)

    @Delete
     fun delete(entity: EmployeeEntity)

    @Query("select * from employee")
     fun fetchAll() : List<EmployeeEntity>

    @Query("select sum(salary) from employee")
     fun fetchSumSalary() : Float?

     @Query("select avg(salary) from employee")
     fun fetchAvgSalary() : Float?

     @Query("select avg(salary) from employee where location='Budapest'")
     fun fetchAvgSalaryBudapest() : Float?

     @Query("select avg(salary) from employee where location='Miskolc'")
     fun fetchAvgSalaryMiskolc() : Float?

     @Query("select count(*) from employee")
     fun fetchCountEmployees() : Int?

     @Query("select count(*) from employee where location='Budapest'")
     fun fetchCountEmployeesBudapest() : Int?

     @Query("select count(*) from employee where location='Miskolc'")
     fun fetchCountEmployeesMiskolc() : Int?

     @Query("select * from employee where id = :id")
     fun fetchEmployeeById(id: Int) : EmployeeEntity?
}