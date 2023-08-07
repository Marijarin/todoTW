package com.marijarin.mytodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.marijarin.mytodo.R
import com.marijarin.mytodo.adapter.ItemAdapter
import com.marijarin.mytodo.adapter.OnItemListener
import com.marijarin.mytodo.databinding.TodoListFragmentBinding
import com.marijarin.mytodo.dto.Item
import com.marijarin.mytodo.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoListFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val binding = TodoListFragmentBinding.inflate(inflater, container, false)
        val adapter = ItemAdapter(object : OnItemListener {
            override fun onRemove(item: Item) {
                viewModel.removeById(item.id)
            }
        })
        binding.list.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.data.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_newDoFragment)
        }
        return binding.root
    }

}