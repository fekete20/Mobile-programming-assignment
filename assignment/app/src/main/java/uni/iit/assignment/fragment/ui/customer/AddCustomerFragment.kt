package uni.iit.assignment.fragment.ui.customer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import uni.iit.assignment.R
import uni.iit.assignment.databinding.FragmentAddCustomerBinding
import uni.iit.assignment.domain.Customer

class AddCustomerFragment : Fragment() {

    private val viewModel : AddCustomerViewModel by inject()
    private var _binding : FragmentAddCustomerBinding? = null
    private val binding get () = _binding!!

    companion object {
        fun newInstance() = AddCustomerFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCustomerBinding.inflate(inflater, container, false)
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
        binding.saveCustomerButton.setOnClickListener {
            val name = binding.textCustomerName.text.toString()
            val location = binding.textCustomerLocation.text.toString()
            val comment = binding.textCustomerComment.text.toString()
            val customer = Customer(0, name, location, comment)
            viewModel.save(customer)
            Snackbar.make(view, "Tárolva.", Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}