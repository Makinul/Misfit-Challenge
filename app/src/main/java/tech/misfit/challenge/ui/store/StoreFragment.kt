package tech.misfit.challenge.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tech.misfit.challenge.MainActivity
import tech.misfit.challenge.R
import tech.misfit.challenge.base.BaseFragment
import tech.misfit.challenge.data.model.ProductInfo
import tech.misfit.challenge.data.model.StoreInfo
import tech.misfit.challenge.data.network.helper.Status
import tech.misfit.challenge.databinding.FragmentStoreBinding
import tech.misfit.challenge.utils.NetworkConnection

@AndroidEntryPoint
class StoreFragment : BaseFragment() {

    private val viewModel: StoreViewModel by viewModels()
    private var _binding: FragmentStoreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.storeInfo.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    showProgressDialog()
                }
                Status.ERROR -> {
                    closeProgressDialog()
                    if (NetworkConnection.getInstance().isConnected) {
                        showSimpleDialog(it.message)
                    } else {
                        showSimpleDialog(getString(R.string.no_network))
                    }
                }
                Status.SUCCESS -> {
                    closeProgressDialog()
                    it.data?.let { storeInfo ->
                        updateStoreInfoView(storeInfo)
                    }
                }
            }
        })

        viewModel.productList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                    binding.statusLay.statusLay.visibility = View.INVISIBLE
                }
                Status.ERROR -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.statusLay.statusLay.visibility = View.VISIBLE
                    val message = if (NetworkConnection.getInstance().isConnected) {
                        it.message
                    } else {
                        getString(R.string.no_network)
                    }
                    binding.statusLay.statusMessage.text = message

                    products.clear()
                    adapter.notifyDataSetChanged()
                }
                Status.SUCCESS -> {
                    binding.statusLay.statusLay.visibility = View.INVISIBLE
                    binding.swipeRefreshLayout.isRefreshing = false

                    products.clear()
                    it.data?.let { list ->
                        products.addAll(list)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

        binding.checkout.setOnClickListener {
            val selectedItems = adapter.getSelectedItems()
            this@StoreFragment.selectedItems.clear()
            this@StoreFragment.selectedItems.addAll(selectedItems)
            if (this@StoreFragment.selectedItems.isEmpty()) {
                showToast(R.string.please_add_some_products_to_checkout)
                return@setOnClickListener
            }
            if (activity is MainActivity) {
                (activity as MainActivity).navigateTo(
                    R.id.navSummary,
                    this@StoreFragment.selectedItems
                )
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getProductList()
        }

        return root
    }

    private fun updateStoreInfoView(storeInfo: StoreInfo) {
        binding.name.text = storeInfo.name
        binding.rating.rating = storeInfo.rating

        binding.openingTime.text = storeInfo.openingTime
        binding.closingTime.text = storeInfo.closingTime
    }

    private lateinit var adapter: ProductAdapter
    private val selectedItems: ArrayList<ProductInfo> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(products, object : ProductAdapter.ProductListener {
            override fun itemChangedToCart() {
                val selectedItems = adapter.getSelectedItems()
                this@StoreFragment.selectedItems.clear()
                this@StoreFragment.selectedItems.addAll(selectedItems)
                showLog()
            }
        })
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        viewModel.getStoreInfo()
        viewModel.getProductList()
    }

    override fun onResume() {
        super.onResume()

        if (activity is MainActivity)
            (activity as MainActivity).updateTitle(R.string.menu_store)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val products: ArrayList<ProductInfo> = ArrayList()
    }
}