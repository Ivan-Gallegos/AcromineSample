package com.example.acrominesample

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.acrominesample.models.LfsItem
import com.example.acrominesample.models.ResponseItem
import com.example.acrominesample.network.Repository
import retrofit2.Call
import retrofit2.Callback

class MainActivityViewModel : ViewModel() {
    private val repository = Repository()
    private val _list = MutableLiveData<List<LfsItem?>>()

    val list: LiveData<List<LfsItem?>> = _list

    fun callSf(abbreviation: String) {
        repository.callSf(abbreviation).enqueue(object : Callback<List<ResponseItem>> {

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                Log.e("TAG", "ERROR", t)
            }

            override fun onResponse(
                call: Call<List<ResponseItem>>, response: retrofit2.Response<List<ResponseItem>>
            ) {
                _list.value = response.body()?.getOrNull(0)?.lfs
                Log.d("TAG", response.body().toString())
            }

        })
    }

    companion object {
        @BindingAdapter("app:lf_list")
        @JvmStatic
        fun setLfList(recycler: RecyclerView, list: List<LfsItem?>?) {
            recycler.adapter = LfAdapter(list)
        }
    }

}