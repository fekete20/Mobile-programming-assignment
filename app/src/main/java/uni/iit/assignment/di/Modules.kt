import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uni.iit.assignment.db.AssignmentDatabase
import uni.iit.assignment.fragment.ui.customer.AddCustomerViewModel
import uni.iit.assignment.fragment.ui.employee.AddEmployeeViewModel
import uni.iit.assignment.fragment.ui.employee.EmployeeViewModel
import uni.iit.assignment.fragment.ui.home.HomeViewModel
import uni.iit.assignment.fragment.ui.customer.CustomerViewModel

val appModule = module {
    single { AssignmentDatabase.getInstance(androidContext()) }
    viewModel { EmployeeViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CustomerViewModel(get(), get(), get()) }
    viewModel { AddEmployeeViewModel(get()) }
    viewModel { AddCustomerViewModel(get()) }

}