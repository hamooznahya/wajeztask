package com.example.wajeztask.presentation.home.elixirs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.wajeztask.R
import com.example.wajeztask.presentation.home.HomePageEvents
import com.example.wajeztask.presentation.home.wizardsdetails.WizardDetailScreen
import com.example.wajeztask.presentation.home.wizardsdetails.WizardDetailsModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ElixirsDetailsFragment : Fragment() {

    private val viewModel by viewModels<ElixirsDetailsModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent {
                MaterialTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        ElixirsDetailScreen(viewModel)
                    }
                }
            }
        }
    }
}