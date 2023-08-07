package com.marijarin.mytodo.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import com.marijarin.mytodo.R
import com.marijarin.mytodo.databinding.ActivityMainBinding
import com.marijarin.mytodo.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ItemViewModel by viewModels()
    private var menuProvider: MenuProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                when (menuItem.itemId) {
                    R.id.clear -> {
                        viewModel.removeAll()
                        true
                    }
                    R.id.changeOrder -> {
                       viewModel.changeOrder(!viewModel.choice.value)
                        true
                    }
                    else -> false
                }
        }.apply {
            menuProvider = this
        })

    }
}