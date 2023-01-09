package be.g55990.messaging

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import be.g55990.messaging.viewmodel.LoginViewModal
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginViewModal= ViewModelProvider(this)[LoginViewModal::class.java]
        val navView: NavigationView = findViewById(R.id.navView)
        val drawerLayout: DrawerLayout = findViewById(R.id.main_container)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        fun loginMiddleware(isLogged : Boolean){
            if(isLogged) {
                navController.navigate(R.id.action_global_navigation_message)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }else{
                navController.navigate(R.id.action_global_fragment_login)
                drawerLayout.close()
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }

        }

        val loggedObserver = Observer<Boolean>{
            loginMiddleware(it)
        }

        loginViewModal.logged.observe(this, loggedObserver)

        val signoutButton = findViewById<Button>(R.id.signout)
        signoutButton.setOnClickListener {
            loginViewModal.signOut()
        }
    }

}
