package tech.misfit.challenge

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.annotation.StringRes
import dagger.hilt.android.AndroidEntryPoint
import tech.misfit.challenge.base.BaseActivity
import tech.misfit.challenge.data.model.ProductInfo
import tech.misfit.challenge.databinding.ActivityMainBinding
import tech.misfit.challenge.utils.AppConstants

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        updateTitle()
    }

    fun updateTitle(@StringRes title: Int = R.string.menu_store) {
        supportActionBar?.title = getString(title)
    }

    private var currentFragmentId = -1

    fun navigateTo(navigateId: Int, products: ArrayList<ProductInfo>) {
        if (currentFragmentId == navigateId)
            return
        currentFragmentId = navigateId
        val navController = findNavController(R.id.mainNavHostFragment)
        val args = Bundle()
        args.putParcelableArrayList(AppConstants.KEY_PRODUCTS, products)
        navController.navigate(navigateId, args)
    }

    override fun onBackPressed() {
        currentFragmentId = -1
        super.onBackPressed()
    }
}