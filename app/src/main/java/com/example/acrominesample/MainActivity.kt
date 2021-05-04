package com.example.acrominesample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acrominesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.etRequest.let {
            it.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val text = it.text.toString()
                    viewModel.callSf(text)
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }

        binding.rvResponse.layoutManager = LinearLayoutManager(this)
    }
}
