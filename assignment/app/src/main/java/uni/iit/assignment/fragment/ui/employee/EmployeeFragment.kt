package uni.iit.assignment.fragment.ui.employee

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
import uni.iit.assignment.databinding.FragmentEmployeeBinding

class EmployeeFragment : Fragment() {
    private val employeeViewModel: EmployeeViewModel by inject()

    private var _binding: FragmentEmployeeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var adapter: EmployeeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = EmployeeAdapter()

        val employeeList: RecyclerView = binding.employeeList
        employeeList.layoutManager = LinearLayoutManager(context)
        employeeList.adapter = adapter

        val divider = DividerItemDecoration(employeeList.context, LinearLayoutManager.VERTICAL)
        employeeList.addItemDecoration(divider)

        val swipeToDeleteItemDecoration = SwipeToDeleteItemDecoration()
        employeeList.addItemDecoration(swipeToDeleteItemDecoration)

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                moveDecorationToTop(swipeToDeleteItemDecoration)
                employeeList.addItemDecoration(swipeToDeleteItemDecoration)
                val position = viewHolder.adapterPosition
                val item = adapter?.apply {
                    employeeViewModel.deleteEmployee(this.items[position])
                }
            }
        }

        var deleteHelper = ItemTouchHelper(swipeToDeleteCallback)
        deleteHelper.attachToRecyclerView(employeeList)

        val swipeToUpdateItemDecoration = SwipeToUpdateItemDecoration()
        employeeList.addItemDecoration(swipeToUpdateItemDecoration)

        val swipeToUpdateCallback = object : SwipeToUpdateCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                moveDecorationToTop(swipeToUpdateItemDecoration)
                employeeList.addItemDecoration(swipeToUpdateItemDecoration)
                var builder = AlertDialog.Builder(activity!!)
                builder.setCancelable(true)
                var newName = EditText(this@EmployeeFragment.context)
                var newPost = EditText(this@EmployeeFragment.context)
                var newLocation = EditText(this@EmployeeFragment.context)
                var newSalary = EditText(this@EmployeeFragment.context)

                builder.setTitle("Elem módosítása")
                builder.setMessage("Írja be az új adatokat!")
                val layout = LinearLayout(this@EmployeeFragment.context)
                layout.orientation = LinearLayout.VERTICAL

                newName.setSingleLine()
                newName.hint = "Új név"
                layout.addView(newName )

                newPost.setSingleLine()
                newPost.hint = "Új beosztás"
                layout.addView(newPost)

                newLocation.setSingleLine()
                newLocation.hint = "Új telephely"
                layout.addView(newLocation)

                newSalary.setSingleLine()
                newSalary.hint = "Új fizetés értéke"
                layout.addView(newSalary)

                builder.setView(layout)

                builder.setNegativeButton("Mégsem", DialogInterface.OnClickListener { dialog, which ->
                    moveDecorationToTop(swipeToUpdateItemDecoration)
                    employeeList.addItemDecoration(swipeToUpdateItemDecoration)
                    val position = viewHolder.adapterPosition
                    val item = adapter?.apply {
                        employeeViewModel.editEmployee(this.items[position])
                    }
                })

                builder.setPositiveButton("Módosítás", DialogInterface.OnClickListener { dialog, which ->
                    moveDecorationToTop(swipeToUpdateItemDecoration)
                    employeeList.addItemDecoration(swipeToUpdateItemDecoration)
                    val position = viewHolder.adapterPosition
                    val item = adapter?.apply {
                        this.items[position].name = newName.text.toString()
                        this.items[position].position = newPost.text.toString()
                        this.items[position].location = newLocation.text.toString()
                        this.items[position].salary = newSalary.text.toString().toInt()
                        employeeViewModel.editEmployee(this.items[position])
                    }

                Snackbar.make(binding.root, "Módosítva.", Snackbar.LENGTH_LONG).show()
                })

                builder.show()

            }
        }

        var updateHelper = ItemTouchHelper(swipeToUpdateCallback)
        updateHelper.attachToRecyclerView(employeeList)

        binding.fab.setOnClickListener { view ->
            findNavController().navigate(R.id.action_nav_employee_to_addEmployeeFragment)
        }

        employeeViewModel.employees.observe(viewLifecycleOwner, {
            adapter?.items?.clear()
            adapter?.items?.addAll(it)
            adapter?.notifyDataSetChanged()

        })
        employeeViewModel.deletedEmployee.observe(viewLifecycleOwner, { deleted ->
            adapter?.items?.indexOfFirst { it?.id == deleted.id }?.let { index ->
                adapter?.items?.removeAt(index)
                adapter?.notifyItemRemoved(index)
                Snackbar.make(binding.root, "Törölve.", Snackbar.LENGTH_LONG).show()
            }
        })

        employeeViewModel.updatedEmployee.observe(viewLifecycleOwner, { updated ->
            adapter?.items?.indexOfFirst { it?.id == updated.id }?.let { index ->
                adapter?.items?.set(index, updated)
                adapter?.notifyItemChanged(index)
            }
        })
        employeeViewModel.loadEmployees()
        return root
    }

    private fun moveDecorationToTop(decoration: RecyclerView.ItemDecoration) {
        if(binding.employeeList.getItemDecorationAt(0) != decoration) {
            binding.employeeList.removeItemDecorationAt(1)
            binding.employeeList.addItemDecoration(decoration , 0)
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