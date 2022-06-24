package uni.iit.assignment.db.datasource

import uni.iit.assignment.data.SummaryDataSource
import uni.iit.assignment.db.dao.CustomerDao
import uni.iit.assignment.db.dao.EmployeeDao
import uni.iit.assignment.domain.Summary

class RoomSummaryDataSource(private val employeeDao: EmployeeDao, private val customerDao: CustomerDao) :
    SummaryDataSource {
    override suspend fun fetchSummary(): Summary {
        val totalSalary : Float = employeeDao.fetchSumSalary() ?: 0f
        val avgSalary : Float = employeeDao.fetchAvgSalary() ?: 0f
        var avgSalaryBudapest : Float = employeeDao.fetchAvgSalaryBudapest() ?: 0f
        var avgSalaryMiskolc : Float = employeeDao.fetchAvgSalaryMiskolc() ?: 0f
        var countEmployees : Int = employeeDao.fetchCountEmployees() ?: 0
        var countEmployeesBudapest : Int = employeeDao.fetchCountEmployeesBudapest() ?: 0
        var countEmployeesMiskolc : Int = employeeDao.fetchCountEmployeesMiskolc() ?: 0
        var countCustomers : Int = customerDao.fetchCountCustomers() ?: 0


        return Summary(totalSalary, avgSalary, avgSalaryBudapest, avgSalaryMiskolc, countEmployees, countEmployeesBudapest, countEmployeesMiskolc, countCustomers)
    }
}