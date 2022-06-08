package com.example.codechallenge.ordersModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codechallenge.PizzaDetailFragment
import com.example.codechallenge.R
import com.example.codechallenge.SaladDetailFragment
import com.example.codechallenge.common.entities.OrderEntitiy
import com.example.codechallenge.databinding.FragmentOrdersBinding
import com.example.codechallenge.ordersModule.adapters.OrderAdapter

class OrdersFragment : Fragment(), OnClickListener {

    private lateinit var mBinding: FragmentOrdersBinding
    private lateinit var mViewModel: OrdersViewModel
    private lateinit var mAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_orders, null)
        mBinding = FragmentOrdersBinding.bind(view)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
        setUpViewModel()
    }

    private fun setUpList() {
        mAdapter = OrderAdapter(emptyList(), this)
        mBinding.recyclerOrders.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)
        mViewModel.getOrders().observe(viewLifecycleOwner) { orders ->
            mAdapter.setOrders(orders)
        }
        mViewModel.loadOrders()
    }


    private fun launchDetailOrderFragment(order: OrderEntitiy) {
        var fragment: Fragment?


        when (order.type) {
            OrderEntitiy.TYPE_PIZZA -> {
                fragment = PizzaDetailFragment()
            }
            OrderEntitiy.TYPE_SALAD -> {
                fragment = SaladDetailFragment()
            }
            else -> {
                fragment = PizzaDetailFragment()
            }
        }

        PizzaDetailFragment()


        val bundle = Bundle()
        bundle.putSerializable(PizzaDetailFragment.ORDER_PARAM, order)

        fragment.arguments = bundle

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.containerMain, fragment)
        fragmentTransaction?.addToBackStack("OrdersFragment")
        fragmentTransaction?.commit()
    }

    override fun onClick(order: OrderEntitiy) {
        launchDetailOrderFragment(order)
    }

}