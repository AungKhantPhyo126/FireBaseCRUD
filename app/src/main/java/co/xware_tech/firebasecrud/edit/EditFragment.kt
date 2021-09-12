package co.xware_tech.firebasecrud.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.xware_tech.firebasecrud.R
import co.xware_tech.firebasecrud.databinding.FragmentEditBinding

class EditFragment: Fragment() {
    private lateinit var binding:FragmentEditBinding
    private val viewModel by viewModels<EditViewModel>()

    private val args by navArgs<EditFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentEditBinding.inflate(inflater,container,false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            if (binding.tieName.text.isNullOrEmpty()){
                Toast.makeText(context,"Invalid Name", Toast.LENGTH_LONG).show()
            }else {
                viewModel.updateName(args.studentId,binding.tieName.text.toString())
                findNavController().popBackStack()
            }
        }
    }
}