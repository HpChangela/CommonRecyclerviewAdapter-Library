package com.example.commonrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.common_recyclerview_adapter.CommonRecyclerViewAdapter
import com.example.commonrecyclerview.databinding.ActivityMainBinding
import com.example.commonrecyclerview.databinding.RowAppointmentBinding
import com.example.commonrecyclerview.model.Product

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CommonRecyclerViewAdapter<Product, RowAppointmentBinding>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        adapter = object : CommonRecyclerViewAdapter<Product, RowAppointmentBinding>(
            context = this,
            layoutId = R.layout.row_appointment
        ) {
            override fun getViewData(
                itemView: RowAppointmentBinding,
                position: Int,
                itemList: MutableList<Product>
            ) {
                itemView.tvText.text = itemList[position].name
            }
        }

        binding.rvMain.apply {
            this.adapter = adapter
        }

        val list = mutableListOf<Product>()

        list.add(Product(id = "1", "Hp Laptop"))
        list.add(Product(id = "2", "Dell Laptop"))
        list.add(Product(id = "3", "Apple Laptop"))

        adapter.addData(list)
    }

}