package tech.misfit.challenge.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tech.misfit.challenge.MainActivity
import tech.misfit.challenge.R
import tech.misfit.challenge.base.BaseFragment
import tech.misfit.challenge.databinding.FragmentSummaryBinding

@AndroidEntryPoint
class SummaryFragment : BaseFragment() {

    private val viewModel: SummaryViewModel by viewModels()
    private var _binding: FragmentSummaryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onResume() {
        super.onResume()

        if (activity is MainActivity)
            (activity as MainActivity).updateTitle(R.string.menu_summary)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}