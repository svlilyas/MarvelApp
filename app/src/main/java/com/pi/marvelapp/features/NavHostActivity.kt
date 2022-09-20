package com.pi.marvelapp.features

import android.os.Bundle
import android.widget.Toast
import com.pi.marvelapp.R
import com.pi.marvelapp.databinding.ActivityNavHostBinding
import com.pi.marvelapp.core.extensions.navigateSafe
import com.pi.marvelapp.core.extensions.observe
import com.pi.marvelapp.core.navigation.NavManager
import com.pi.marvelapp.core.network.ConnectivityObserver
import com.pi.marvelapp.core.platform.BaseActivity
import com.pi.marvelapp.core.platform.ProjectApplication.Companion.connectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NavHostActivity : BaseActivity<ActivityNavHostBinding, MainViewModel>(
    layoutId = R.layout.activity_nav_host,
    viewModelClass = MainViewModel::class.java
) {

    @Inject
    lateinit var navManager: NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNavManager()
    }

    override fun setUpViews() {
        super.setUpViews()

        observe(connectivityObserver.observe()) { connectionStatus ->
            val status = when (connectionStatus) {
                ConnectivityObserver.ConnectionStatus.Available -> "Available"
                ConnectivityObserver.ConnectionStatus.Unavailable -> "Unavailable"
                ConnectivityObserver.ConnectionStatus.Losing -> "Losing"
                ConnectivityObserver.ConnectionStatus.Lost -> "Lost"
            }

            Toast.makeText(this@NavHostActivity, status, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }
    }
}
