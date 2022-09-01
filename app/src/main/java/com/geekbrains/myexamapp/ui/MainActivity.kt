package com.geekbrains.myexamapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.geekbrains.myexamapp.databinding.ActivityMainBinding
import com.geekbrains.myexamapp.ui.classes_fragment.ClassesFragment
import com.geekbrains.myexamapp.ui.home_fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
            if (savedInstanceState == null) {
                openFragment(HomeFragment())
            }
    }

    fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(bind.container.id, fragment)
            .commitNow()
    }

    fun openFragmentOnItem(position: Int) {
        openFragment(ClassesFragment(position))
    }
}
