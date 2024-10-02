package com.example.wajeztask.presentation.home.homefragment

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
import com.example.wajeztask.utils.observeAsEvent
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    companion object{
        const val WIZARD_ID="wizardId"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent {
                MaterialTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {

                        viewModel.events.observeAsEvent {
                            when (it) {
                                is HomePageEvents.OpenWizardDetailPage -> {
                                    findNavController().navigate(
                                        R.id.action_global_wizardDetailsFragment,
                                        Bundle().apply { putString(WIZARD_ID, it.wizardId) })
                                }

                                else -> {}
                            }
                        }
                                WizardListScreen(viewModel,viewModel::onAction)
                    }
                }
            }
        }
    }
}
