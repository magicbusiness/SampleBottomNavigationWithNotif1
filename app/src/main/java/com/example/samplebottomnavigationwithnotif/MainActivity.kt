package com.example.samplebottomnavigationwithnotif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFrag = SampleFragment()
        val messageFrag = SampleFragment2()
        val personFrag = PersonFragment()

        val nav_bottom: BottomNavigationView = findViewById(R.id.nav_bottom)

        // Initialize Fragment
        fragment(homeFrag)

        nav_bottom.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_menu -> fragment(homeFrag)
                R.id.message_menu -> fragment(messageFrag)
                R.id.person_menu -> fragment(personFrag)
            }
            true
        }

        // Must Implement MaterialComponent in theme
        // Notification Badge
        nav_bottom.getOrCreateBadge(R.id.message_menu).apply {
            number = 10
            isVisible = true
        }

        val btn_increase: Button = findViewById(R.id.button_increase)
        val btn_decrease: Button = findViewById(R.id.button_decrease)
        val btn_clear: Button = findViewById(R.id.button_clear)

        btn_increase.setOnClickListener {
            nav_bottom.getOrCreateBadge(R.id.message_menu).apply {
                number += 1
                isVisible = true
            }
        }

        btn_decrease.setOnClickListener {
            nav_bottom.getOrCreateBadge(R.id.message_menu).apply {
                number -= 1
                isVisible = true
            }
        }

        btn_clear.setOnClickListener {
            nav_bottom.getOrCreateBadge(R.id.message_menu).apply {
                number = 0
                isVisible = false
            }
        }
    }


    private fun fragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }
}