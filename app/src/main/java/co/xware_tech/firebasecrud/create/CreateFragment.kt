package co.xware_tech.firebasecrud.create

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.xware_tech.firebasecrud.databinding.FragmentCreateBinding
import co.xware_tech.firebasecrud.hideKeyboard


class CreateFragment:Fragment() {
    private lateinit var binding:FragmentCreateBinding
    private val viewModel by viewModels<CreateViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCreateBinding.inflate(inflater,container,false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tieName.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // If the event is a key-down event on the "enter" button
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    // Perform action on key press
                    hideKeyboard(activity, binding.tieName)
                    return true
                }
                return false
            }
        })

        binding.btnCreate.setOnClickListener {
            if (binding.tieName.text.isNullOrEmpty()){
                Toast.makeText(context,"Invalid Name",Toast.LENGTH_LONG).show()
            }else{
               viewModel.addName(binding.tieName.text.toString())
                findNavController().popBackStack()
            }
        }


    }

}