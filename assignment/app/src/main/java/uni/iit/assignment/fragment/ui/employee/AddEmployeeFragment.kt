package uni.iit.assignment.fragment.ui.employee

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import uni.iit.assignment.R
import uni.iit.assignment.databinding.FragmentAddEmployeeBinding
import uni.iit.assignment.domain.Employee

class AddEmployeeFragment : Fragment() {

    private val viewModel : AddEmployeeViewModel by inject()
    private var _binding : FragmentAddEmployeeBinding? = null
    private val binding get () = _binding!!

    companion object {
        fun newInstance() = AddEmployeeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)
        val root : View = binding.root

        viewModel.result.observe(viewLifecycleOwner, { result ->
            result?.let {
                if (it) {
                    activity?.onBackPressed()
                } else {
                    val builder = AlertDialog.Builder(this.context)
                    builder.setMessage(resources.getString(R.string.errorMessage))
                    builder.setTitle(resources.getString(R.string.errorTitle))
                    val alert = builder.create()
                    alert.show()
                }
            }
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveEmployeeButton.setOnClickListener {
                    val name = binding.textEmployeeName.text.toString()
                    val position = binding.textEmployeePosition.text.toString()
                    val location = binding.textEmployeeLocation.text.toString()
                    val salary = binding.textEmployeeSalary.text.toString().toInt()
                    val employee = Employee(0, name, position, location, salary)
                    viewModel.save(employee)
                    Snackbar.make(view, "Tárolva.", Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}