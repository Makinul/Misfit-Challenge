package tech.misfit.challenge.ui.store

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.misfit.challenge.R
import tech.misfit.challenge.data.model.ProductInfo
import tech.misfit.challenge.databinding.ItemProductBinding
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ProductAdapter(
    private var arrayList: ArrayList<ProductInfo>,
    private val listener: ProductListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val selectedMap: HashMap<Int, Int> = HashMap() // key = position and value = counter

    fun getSelectedItems(): ArrayList<ProductInfo> {
        val selectedArrayList: ArrayList<ProductInfo> = ArrayList()
        for (key in selectedMap.keys) {
            val value = selectedMap[key]

            if (value != null && value > 0) {
                for (i in 0 until value) {
                    selectedArrayList.add(arrayList[key])
                }
            }
        }

        return selectedArrayList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    fun setData(arrayList: ArrayList<ProductInfo>) {
        this.arrayList = arrayList
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    private fun getItem(position: Int): ProductInfo {
        return arrayList[position]
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val holder = viewHolder as ViewHolder
        with(holder) {
            bindData(position, item)
        }
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(viewHolder, position, payloads)
        else {
            showLog()
            val obj = payloads[0]
            if (obj is Boolean) {
                if (obj) {
                    val holder = viewHolder as ViewHolder
                    with(holder) {
                        updateCounter(position)
                    }
                }
            }
        }
    }

    inner class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.decrease.setOnClickListener {
                val value = selectedMap[layoutPosition]
                var counter: Int = value ?: 0

                if (counter == 0)
                    return@setOnClickListener

                counter -= 1
                selectedMap[layoutPosition] = counter

                getSelectedItems()
                notifyItemChanged(layoutPosition, true)
                listener.itemChangedToCart()
            }
            binding.increase.setOnClickListener {
                val value = selectedMap[layoutPosition]
                var counter: Int = value ?: 0
                counter += 1
                selectedMap[layoutPosition] = counter

                getSelectedItems()
                notifyItemChanged(layoutPosition, true)
                listener.itemChangedToCart()
            }
        }

        fun bindData(position: Int, item: ProductInfo) {
            val context = binding.productImage.context
            binding.name.text = item.name

            val unitPrice = context.getString(R.string.unit_price, item.price.toString())
            binding.price.text = unitPrice

            if (item.imageUrl != null) {
                Glide
                    .with(context)
                    .load(item.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_shop)
                    .into(binding.productImage)
            }
            updateCounter(position)
        }

        fun updateCounter(position: Int) {
            val value = selectedMap[position]
            val counter: Int = value ?: 0
            binding.cartCounter.setText(counter.toString())
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface ProductListener {
        fun itemChangedToCart()
    }

    @JvmOverloads
    fun showToast(context: Context, @StringRes resourceId: Int = R.string.work_in_progress) {
        showToast(context, context.getString(resourceId))
    }

    private fun showToast(context: Context, message: String) {
        if (message.isEmpty()) return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLog(message: String = "Test") {
        Log.v("ProductAdapter", message)
    }
}