package ua.uswitch.testwork20.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import ua.uswitch.testwork20.R
import ua.uswitch.testwork20.adapters.FilmAdapter
import ua.uswitch.testwork20.databinding.FragmentListFilmBinding
import ua.uswitch.testwork20.storage.FilmViewModel

@AndroidEntryPoint
class ListFragments: Fragment(), (Int) -> Unit {

    private val filmViewModel: FilmViewModel by viewModels()
    private lateinit var ourFilmAdapter: FilmAdapter
    private var binding: FragmentListFilmBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListFilmBinding.inflate(inflater, container, false)

        ourFilmAdapter = FilmAdapter(this)


        val bind = binding
        if(bind!= null) {
            bind.swipeRefreshList.setOnRefreshListener {
                filmViewModel.getFilm()
                bind.swipeRefreshList.isRefreshing = false
            }

            bind.fragmentMainRecyclerview.adapter = ourFilmAdapter
            return bind.root
        }


        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmViewModel.response.observe(this.viewLifecycleOwner) { response ->

            ourFilmAdapter.updateList(response)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun invoke(p1: Int) {
        val fragments = DetailsFragment()
        val bundle = Bundle()
        bundle.putInt("id", p1)
        fragments.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("userDetails")
            .replace(R.id.fragment_container_mainActivity, fragments)
            .commit()
    }
}