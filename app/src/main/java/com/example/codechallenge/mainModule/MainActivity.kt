package com.example.codechallenge.mainModule

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.codechallenge.ordersModule.OrdersFragment
import com.example.codechallenge.R
import com.example.codechallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViewModel()

    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mViewModel.getEuroPrices().observe(this) { price ->
            if (price.usd == 0.0) {
                Toast.makeText(applicationContext, "Euro Price Failed", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                launchHomeFragment()
            }
        }
    }

    private fun launchHomeFragment(args: Bundle? = null) {
        val fragment = OrdersFragment()
        if (args != null) fragment.arguments = args

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.containerMain, fragment)
        fragmentTransaction.commit()
    }


}
