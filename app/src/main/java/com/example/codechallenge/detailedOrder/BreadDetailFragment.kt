package com.example.codechallenge.detailedOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codechallenge.common.entities.OrderEntitiy
import com.example.codechallenge.databinding.FragmentBreadOrderDetailBinding
import java.text.DecimalFormat

class BreadDetailFragment : Fragment() {


    private var order: OrderEntitiy? = null

    private lateinit var mBinding: FragmentBreadOrderDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            order = bundle.getSerializable(ORDER_PARAM) as OrderEntitiy
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentBreadOrderDetailBinding.inflate(layoutInflater)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        order?.let {

            with(it) {
                mBinding.txtType.text = type
                mBinding.txtShape.text = shape
                mBinding.txtGrain.text = grain

                mBinding.txtPrice.text =   "$${DecimalFormat("0.00").format(calculateDollarsPrice())}"
                mBinding.txtEuroPrice.text =   "€${DecimalFormat("0.00").format(calculatePrice())}"
            }
        }
    }

    companion object {
        const val ORDER_PARAM = "order_param"
    }
}