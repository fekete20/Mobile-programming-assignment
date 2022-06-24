package uni.iit.assignment.fragment.ui.employee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uni.iit.assignment.databinding.EmployeeListItemBinding
import uni.iit.assignment.domain.Employee

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    var items : MutableList<Employee> = mutableListOf()

    inner class EmployeeViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val item = items[position]
            val binding = EmployeeListItemBinding.bind(itemView)
            binding.textEmployeeName.text = "Név: " + item.name
            binding.textEmployeePosition.text = "Beosztás: " + item.position
            binding.textEmployeeLocation.text = "Telephely: " + item.location
            binding.textEmployeeSalary.text = "Fizetés: "+ item.salary.toString() + " HUF"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {

        val binding = EmployeeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding.root)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(position)
    }

}
