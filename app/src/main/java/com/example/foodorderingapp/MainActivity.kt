package com.example.foodorderingapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.navigation.ui.NavigationUI
import com.example.foodorderingapp.databinding.ActivityMainBinding
import com.example.foodorderingapp.model.AccountItem
import com.example.foodorderingapp.ui.AccountFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.itemToolbar.profileIcon.setOnClickListener {
            val accountFragment = AccountFragment()
            val accountItemList = setAccountList()
            accountFragment.setAccountList(accountItemList)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setTransition(TRANSIT_FRAGMENT_OPEN)
            transaction
                .add(android.R.id.content, accountFragment)
                .addToBackStack(null)
                .commit()
        }

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.contentMain.bottomNavigationView, navController)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setAccountList(): List<AccountItem> {
        val accountItemList = mutableListOf<AccountItem>()
        accountItemList.add(AccountItem(0, "Profile", "Divya Jain"))
        accountItemList.add(AccountItem(1, "Payment Methods", "Manage payment methods and Doordash credits"))
        accountItemList.add(AccountItem(2, "Addresses", "2 locations"))
        accountItemList.add(AccountItem(3, "Notifications", "Push"))
        accountItemList.add(AccountItem(4, "Support"))
        accountItemList.add(AccountItem(5, "FAQs"))
        accountItemList.add(AccountItem(6, "Log Out"))
        return accountItemList
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
//            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}