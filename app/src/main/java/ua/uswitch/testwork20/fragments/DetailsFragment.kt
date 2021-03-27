package ua.uswitch.testwork20.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ua.uswitch.testwork20.databinding.FragmentDetailsBinding
import ua.uswitch.testwork20.storage.FilmViewModel

@AndroidEntryPoint
class DetailsFragment: Fragment() {
    private val filmViewModel: FilmViewModel by viewModels()
    private  var binding: FragmentDetailsBinding? = null
    private var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        id = arguments?.getInt("id")
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmViewModel.response.observe(this.viewLifecycleOwner) { response ->
            for (film in response) {
                if (film.ID == id) {
                    binding?.result = film
                    return@observe
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}