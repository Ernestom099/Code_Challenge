package com.example.codechallenge.detailedOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.codechallenge.common.entities.OrderEntitiy
import com.example.codechallenge.databinding.FragmentOrderDetailBinding
import java.text.DecimalFormat

class PizzaDetailFragment : Fragment() {


    private var order: OrderEntitiy? = null

    private lateinit var mBinding: FragmentOrderDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            order = bundle.getSerializable(ORDER_PARAM) as OrderEntitiy
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        mBinding = FragmentOrderDetailBinding.inflate(layoutInflater)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        order?.let {
            with(it) {
                mBinding.txtType.text = type
                mBinding.txtSize.text = size

                toppings?.let {
                    mBinding.txtToppings.visibility = View.VISIBLE
                    mBinding.lstToppings.visibility = View.VISIBLE

                    mBinding.txtPrice.text =   "$${DecimalFormat("0.00").format(calculateDollarsPrice())}"
                    mBinding.txtEuroPrice.text =   "€${DecimalFormat("0.00").format(calculatePrice())}"
                    for (topping in toppings) {
                        mBinding.lstToppings.text =
                            mBinding.lstToppings.text.toString().plus("*${topping}\n")
                    }
                }

                sauce?.let { sauces ->
                    mBinding.txtSauce.visibility = View.VISIBLE
                    mBinding.lstSauce.visibility = View.VISIBLE
                    for (sauce in sauces) {
                        mBinding.lstSauce.text =
                            mBinding.lstSauce.text.toString().plus("*${sauce}\n")
                    }
                }
            }
        }
    }

    companion object {
        const val ORDER_PARAM = "order_param"
    }
}