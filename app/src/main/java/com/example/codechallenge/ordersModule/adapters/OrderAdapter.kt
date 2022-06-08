package com.example.codechallenge.ordersModule.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.ordersModule.OnClickListener
import com.example.codechallenge.R
import com.example.codechallenge.common.entities.OrderEntitiy
import com.example.codechallenge.databinding.ItemOrderBinding
import java.text.DecimalFormat

class OrderAdapter(private var orders: List<OrderEntitiy>, private var listener: OnClickListener) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders.get(position)
        with(holder) {
            binding.txtOrderType.text = order.type
            binding.txtOrderPrice.text = "$${ DecimalFormat("0.00").format(order.calculateDollarsPrice())}"
            binding.txtOrderEuroPrice.text = "€${ DecimalFormat("0.00").format(order.calculatePrice())}"
            setListener(order)
        }

    }


    override fun getItemCount(): Int = orders.size

    fun setOrders(orders: List<OrderEntitiy>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemOrderBinding.bind(view)

        fun setListener(order: OrderEntitiy) {
            with(binding.root) {
                setOnClickListener { listener.onClick(order) }
            }
        }

    }

}