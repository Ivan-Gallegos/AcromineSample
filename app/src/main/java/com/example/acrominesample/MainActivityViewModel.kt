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
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val repository = Repository()
    private val _list = MutableLiveData<List<LfsItem?>>()

    val list: LiveData<List<LfsItem?>> = _list
    val noResultList = listOf(LfsItem(lf = "No Results"))

    fun callSf(abbreviation: String) {
        repository.callSf(abbreviation).enqueue(object : Callback<List<ResponseItem>> {

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                Log.e("TAG", "ERROR", t)
                _list.value = noResultList
            }

            override fun onResponse(
                call: Call<List<ResponseItem>>, response: Response<List<ResponseItem>>
            ) {
                val list = response.body()
                if (list?.isNotEmpty() == true) {
                    _list.value = list[0].lfs
                } else {
                    _list.value = noResultList
                }
                Log.d("TAG", list.toString())
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