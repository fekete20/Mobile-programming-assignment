package uni.iit.assignment.di

import uni.iit.assignment.data.*
import uni.iit.assignment.interactors.*
import org.koin.dsl.module
import uni.iit.assignment.db.AssignmentDatabase
import uni.iit.assignment.db.datasource.RoomCustomerDataSource
import uni.iit.assignment.db.datasource.RoomEmployeeDataSource
import uni.iit.assignment.db.datasource.RoomSummaryDataSource
import uni.iit.assignment.db.mapper.CustomerMapper
import uni.iit.assignment.db.mapper.EmployeeMapper

val daoModule = module {
    single { get<AssignmentDatabase>().employeeDao() }
    single { get<AssignmentDatabase>().customerDao() }
}

val dataSourceModule = module {
    single<EmployeeDataSource> { RoomEmployeeDataSource(get(), EmployeeMapper()) }
    single<CustomerDataSource> { RoomCustomerDataSource(get(), CustomerMapper()) }
    single<SummaryDataSource> { RoomSummaryDataSource(get(), get()) }
}

val repositoryModule = module {
    single<EmployeeRepository>{ EmployeeRepository(get()) }
    single<CustomerRepository> { CustomerRepository(get()) }
    single<SummaryRepository>{ SummaryRepository(get()) }
}

val interactorModule = module {
    single { AddEmployee(get()) }
    single { UpdateEmployee(get()) }
    single { RemoveEmployee(get()) }
    single { GetAllEmployee(get()) }

    single { AddCustomer(get()) }
    single { UpdateCustomer(get()) }
    single { RemoveCustomer(get()) }
    single { GetAllCustomer(get()) }

    single { GetSummary(get()) }

}