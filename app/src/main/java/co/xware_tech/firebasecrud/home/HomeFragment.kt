package co.xware_tech.firebasecrud.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import co.xware_tech.firebasecrud.ViewState
import co.xware_tech.firebasecrud.databinding.FragmentHomeBinding
import co.xware_tech.firebasecrud.showDialog

class HomeFragment: Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeBinding.inflate(inflater,container,false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getName()
        binding.btnCreate.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCreateFragment())
        }
        val adapter=RecyclerAdapter(viewModel) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditFragment(it.id))
        }
        viewModel.progressStateLive.observe(viewLifecycleOwner, Observer {
            when(it){
                is ViewState.Loading->{
                    binding.gpContainer.isVisible = false
                    binding.lavLoadState.isVisible = true
                    binding.lavLoadState.playAnimation()
                    binding.btnCreate.isEnabled=false
                }
                is ViewState.Error->{
                    binding.lavLoadState.isVisible = false
                    binding.lavLoadState.cancelAnimation()
                    showDialog(requireContext(), it.error.message.orEmpty(), view)
                    binding.btnCreate.isEnabled=true
                }
                is ViewState.Success->{
                    binding.gpContainer.isVisible = true
                    binding.lavLoadState.isVisible = false
                    binding.lavLoadState.cancelAnimation()
                    adapter.submitList(it.data)
                    binding.btnCreate.isEnabled=true
                }
            }
        })
        binding.rvStudents.adapter=adapter

    }
}