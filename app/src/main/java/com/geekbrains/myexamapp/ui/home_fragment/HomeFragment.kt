package com.geekbrains.myexamapp.ui.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.myexamapp.R
import com.geekbrains.myexamapp.databinding.FragmentMainLayoutBinding
import com.geekbrains.myexamapp.domain.ClassesState
import com.geekbrains.myexamapp.domain.HomeworkState
import com.geekbrains.myexamapp.ui.MainActivity
import com.geekbrains.myexamapp.ui.classes_fragment.ClassesFragment

class HomeFragment : Fragment() {

    private var _bind: FragmentMainLayoutBinding? = null
    private val bind get() = _bind!!

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val classesAdapter = HomeClassesRecyclerAdapter()
    private val homeworkAdapter = HomeworkRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentMainLayoutBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.classesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bind.homeworkRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bind.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.classes_nav_bar -> (requireActivity() as MainActivity).openFragment(ClassesFragment(0))
            }
            return@setOnItemSelectedListener true
        }
        workLivedata()

        classesAdapter.setOnItemViewClickListener(object : OnItemViewClickListener {
            override fun onItemViewClick(classItem: Int) {
                (requireActivity() as MainActivity).openFragment(ClassesFragment(classItem))
            }
        })
    }

    private fun workLivedata() {
        bind.classesRecycler.adapter =
            classesAdapter
        bind.homeworkRecycler.adapter =
            homeworkAdapter

        val classesObserver =
            Observer<ClassesState> { a ->
                renderClassesData(a)
            }
        val homeworkObserver =
            Observer<HomeworkState> { a ->
                renderHomeworkData(a)
            }

        viewModel.getClassesData().observe(
            viewLifecycleOwner,
            classesObserver
        )
        viewModel.getHomeworkData().observe(
            viewLifecycleOwner,
            homeworkObserver
        )
        viewModel.getClasses()
        viewModel.getHomework()
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

    private fun renderHomeworkData(data: HomeworkState) = when (data) {
        is HomeworkState.Success -> {
            val dataList = data.data
            bind.loadingLayout.visibility = View.GONE
            homeworkAdapter.setData(dataList)
        }
        is HomeworkState.Loading -> {
            bind.loadingLayout.visibility = View.VISIBLE
        }
        is HomeworkState.Error -> {
            bind.loadingLayout.visibility = View.GONE

        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(classItem: Int)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
        classesAdapter.removeOnItemViewClickListener()
    }
}