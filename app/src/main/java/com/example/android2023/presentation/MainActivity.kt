package com.example.android2023.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android2023.R
import com.example.android2023.databinding.ActivityMainBinding
import com.example.android2023.presentation.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                MainFragment()
            )
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
