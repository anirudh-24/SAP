package com.example.sampleapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.example.sampleapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.groupFirstRow.addOnClickListener {
            val intent = Intent(this, DataActivity::class.java)
            intent.putExtra("value", 1)
            startActivity(intent)
        }

        binding.view4.setOnClickListener {
            val intent = Intent(this, DataActivity::class.java)
            intent.putExtra("value", 2)
            startActivity(intent)
        }

    }

    fun Group.addOnClickListener(listener: (view: View) -> Unit) {
        referencedIds.forEach { id ->
            rootView.findViewById<View>(id).setOnClickListener(listener)
        }
    }
}
