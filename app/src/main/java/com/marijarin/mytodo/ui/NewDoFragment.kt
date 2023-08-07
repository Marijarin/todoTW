package com.marijarin.mytodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.marijarin.mytodo.R
import com.marijarin.mytodo.databinding.NewDoFragmentBinding
import com.marijarin.mytodo.dto.Item
import com.marijarin.mytodo.util.AndroidUtils
import com.marijarin.mytodo.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewDoFragment : DialogFragment() {
    private val viewModel: ItemViewModel by activityViewModels()
    private var fragmentBinding: NewDoFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val binding = NewDoFragmentBinding.inflate(inflater, container, false)
        fragmentBinding = binding
        binding.save.setOnClickListener {
            AndroidUtils.hideKeyboard(requireView())

            if (binding.title.text.isNotBlank() && binding.desc.text.isNotBlank()) {
                viewModel.save(Item(
                    title = binding.title.text.toString(),
                    desc = binding.desc.text.toString(),
                    date = System.currentTimeMillis()
                ))
                findNavController().navigate(R.id.action_newDoFragment_to_todoListFragment)
            }
        }
        return binding.root
    }
    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}
