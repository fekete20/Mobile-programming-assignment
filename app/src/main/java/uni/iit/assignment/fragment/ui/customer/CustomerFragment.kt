package uni.iit.assignment.fragment.ui.customer

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import uni.iit.assignment.R
import uni.iit.assignment.databinding.FragmentCustomerBinding

import uni.iit.assignment.fragment.ui.employee.SwipeToDeleteCallback
import uni.iit.assignment.fragment.ui.employee.SwipeToDeleteItemDecoration
import uni.iit.assignment.fragment.ui.employee.SwipeToUpdateCallback
import uni.iit.assignment.fragment.ui.employee.SwipeToUpdateItemDecoration

class CustomerFragment : Fragment() {
    private val customerViewModel: CustomerViewModel by inject()

    private var _binding: FragmentCustomerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var adapter: CustomerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = CustomerAdapter()

        val customerList: RecyclerView = binding.customerList
        customerList.layoutManager = LinearLayoutManager(context)
        customerList.adapter = adapter

        val divider = DividerItemDecoration(customerList.context, LinearLayoutManager.VERTICAL)
        customerList.addItemDecoration(divider)

        val swipeToDeleteItemDecoration = SwipeToDeleteItemDecoration()
        customerList.addItemDecoration(swipeToDeleteItemDecoration)

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                moveDecorationToTop(swipeToDeleteItemDecoration)
                customerList.addItemDecoration(swipeToDeleteItemDecoration)
                val position = viewHolder.adapterPosition
                val item = adapter?.apply {
                    customerViewModel.deleteCustomer(this.items[position])
                }
            }
        }

        var deleteHelper = ItemTouchHelper(swipeToDeleteCallback)
        deleteHelper.attachToRecyclerView(customerList)

        val swipeToUpdateItemDecoration = SwipeToUpdateItemDecoration()
        customerList.addItemDecoration(swipeToUpdateItemDecoration)

        val swipeToUpdateCallback = object : SwipeToUpdateCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                moveDecorationToTop(swipeToUpdateItemDecoration)
                customerList.addItemDecoration(swipeToUpdateItemDecoration)
                var builder = AlertDialog.Builder(activity!!)
                builder.setCancelable(true)
                var newName = EditText(this@CustomerFragment.context)
                var newLocation = EditText(this@CustomerFragment.context)
                var newComment = EditText(this@CustomerFragment.context)

                builder.setTitle("Elem módosítása")
                builder.setMessage("Írja be az új adatokat!")
                val layout = LinearLayout(this@CustomerFragment.context)
                layout.orientation = LinearLayout.VERTICAL

                newName.setSingleLine()
                newName.hint = "Új név"
                layout.addView(newName )

                newLocation.setSingleLine()
                newLocation.hint = "Új cím"
                layout.addView(newLocation)

                newComment.setSingleLine()
                newComment.hint = "Új komment"
                layout.addView(newComment)

                builder.setView(layout)

                builder.setNegativeButton("Mégsem", DialogInterface.OnClickListener { dialog, which ->
                    moveDecorationToTop(swipeToUpdateItemDecoration)
                    customerList.addItemDecoration(swipeToUpdateItemDecoration)
                    val position = viewHolder.adapterPosition
                    val item = adapter?.apply {
                        customerViewModel.editCustomer(this.items[position])
                    }
                })

                builder.setPositiveButton("Módosítás", DialogInterface.OnClickListener { dialog, which ->
                    moveDecorationToTop(swipeToUpdateItemDecoration)
                    customerList.addItemDecoration(swipeToUpdateItemDecoration)
                    val position = viewHolder.adapterPosition
                    val item = adapter?.apply {
                        this.items[position].name = newName.text.toString()
                        this.items[position].location = newLocation.text.toString()
                        this.items[position].comment = newComment.text.toString()

                        customerViewModel.editCustomer(this.items[position])
                    }

                    Snackbar.make(binding.root, "Módosítva.", Snackbar.LENGTH_LONG).show()

                })

                builder.show()

            }
        }

        var updateHelper = ItemTouchHelper(swipeToUpdateCallback)
        updateHelper.attachToRecyclerView(customerList)

        binding.fab.setOnClickListener { view ->
            findNavController().navigate(R.id.action_nav_customer_to_addCustomerFragment)
        }

        customerViewModel.customers.observe(viewLifecycleOwner, {
            adapter?.items?.clear()
            adapter?.items?.addAll(it)
            adapter?.notifyDataSetChanged()

        })
        customerViewModel.deletedCustomer.observe(viewLifecycleOwner, { deleted ->
            adapter?.items?.indexOfFirst { it?.id == deleted.id }?.let { index ->
                adapter?.items?.removeAt(index)
                adapter?.notifyItemRemoved(index)
                Snackbar.make(binding.root, "Törölve.", Snackbar.LENGTH_LONG).show()
            }
        })

        customerViewModel.updatedCustomer.observe(viewLifecycleOwner, { updated ->
            adapter?.items?.indexOfFirst { it?.id == updated.id }?.let { index ->
                adapter?.items?.set(index, updated)
                adapter?.notifyItemChanged(index)
            }
        })
        customerViewModel.loadCustomers()
        return root
    }

    private fun moveDecorationToTop(decoration: RecyclerView.ItemDecoration) {
        if(binding.customerList.getItemDecorationAt(0) != decoration) {
            binding.customerList.removeItemDecorationAt(1)
            binding.customerList.addItemDecoration(decoration , 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}