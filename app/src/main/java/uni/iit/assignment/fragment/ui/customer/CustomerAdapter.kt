package uni.iit.assignment.fragment.ui.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uni.iit.assignment.databinding.CustomerListItemBinding
import uni.iit.assignment.domain.Customer

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    var items : MutableList<Customer> = mutableListOf()

    inner class CustomerViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val item = items[position]
            val binding = CustomerListItemBinding.bind(itemView)
            binding.textCustomerName.text = "Név: " + item.name
            binding.textCustomerLocation.text = "Cím: " + item.location
            binding.textCustomerComment.text = "Komment: " + item.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {

        val binding = CustomerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding.root)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bind(position)
    }

}
