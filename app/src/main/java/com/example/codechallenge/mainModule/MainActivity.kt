package com.example.codechallenge.mainModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codechallenge.ordersModule.OrdersFragment
import com.example.codechallenge.R
import com.example.codechallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(mBinding.root)
        launchHomeFragment()
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
