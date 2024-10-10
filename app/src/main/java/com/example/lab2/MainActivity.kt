package com.example.lab2

import ViewPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.viewpager.widget.ViewPager
import com.example.lab2.fragments.CoalFragment
import com.example.lab2.fragments.FuelOilFragment
import com.example.lab2.fragments.NaturalGasFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val fragmentContainer: FragmentContainerView = findViewById(R.id.fragmentContainer)


        // Встановлюємо обробник натискання на вкладки
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> replaceFragment(CoalFragment())
                    1 -> replaceFragment(FuelOilFragment())
                    2 -> replaceFragment(NaturalGasFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // Відображаємо перший фрагмент за замовчуванням
        if (savedInstanceState == null) {
            replaceFragment(CoalFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}