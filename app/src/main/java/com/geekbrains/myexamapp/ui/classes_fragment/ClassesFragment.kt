package com.geekbrains.myexamapp.ui.classes_fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.myexamapp.R
import com.geekbrains.myexamapp.databinding.FragmentClassesLayoutBinding
import com.geekbrains.myexamapp.domain.ClassesState
import com.geekbrains.myexamapp.ui.MainActivity
import com.geekbrains.myexamapp.ui.home_fragment.HomeFragment

class ClassesFragment(private val pos: Int) : Fragment() {

    private var _bind: FragmentClassesLayoutBinding? = null
    private val bind get() = _bind!!

    private val viewModel: ClassesViewModel by lazy {
        ViewModelProvider(this).get(ClassesViewModel::class.java)
    }
    private val classesAdapter = ClassesRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentClassesLayoutBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        bind.classesPageRecycler.layoutManager = layoutManager
        workLivedata()
        bind.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_navi_bar -> (requireActivity() as MainActivity).openFragment(HomeFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun workLivedata() {
        bind.classesPageRecycler.adapter =
            classesAdapter

        val classesObserver =
            Observer<ClassesState> { a ->
                renderClassesData(a)
            }

        viewModel.getClassesData().observe(
            viewLifecycleOwner,
            classesObserver
        )
        viewModel.getClasses()

        if (this.pos != 0) {
            Handler ().postDelayed(Runnable {
                bind.classesPageRecycler.smoothScrollToPosition(
                    pos
                )
            }, 50)
        }
    }

    private fun renderClassesData(data: ClassesState) = when (data) {
        is ClassesState.Success -> {
            val dataList = data.data
            bind.loadingLayout.visibility = View.GONE
            classesAdapter.setData(dataList)
        }
        is ClassesState.Loading -> {
            bind.loadingLayout.visibility = View.VISIBLE
        }
        is ClassesState.Error -> {
            bind.loadingLayout.visibility = View.GONE

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }
}