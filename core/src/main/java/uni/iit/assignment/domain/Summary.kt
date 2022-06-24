package uni.iit.assignment.domain

data class Summary(
    val totalSalary: Float,
    val avgSalary: Float,
    val avgSalaryBudapest: Float?,
    val avgSalaryMiskolc: Float?,
    val countEmployees: Int?,
    val countEmployeesBudapest: Int?,
    val countEmployeesMiskolc: Int?,
    val countCustomers: Int?
)
