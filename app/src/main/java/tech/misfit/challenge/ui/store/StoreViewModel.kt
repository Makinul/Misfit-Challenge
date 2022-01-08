package tech.misfit.challenge.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.misfit.challenge.data.model.ProductInfo
import tech.misfit.challenge.data.model.StoreInfo
import tech.misfit.challenge.data.network.helper.Resource
import tech.misfit.challenge.data.repository.StoreRepository
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val repository: StoreRepository
) : ViewModel() {

    private val _storeInfo = MutableLiveData<Resource<StoreInfo>>()
    val storeInfo: LiveData<Resource<StoreInfo>> = _storeInfo

    fun getStoreInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getStoreInfo().let {
                _storeInfo.postValue(it)
            }
        }
    }

    private val _productList = MutableLiveData<Resource<List<ProductInfo>>>()
    val productList: LiveData<Resource<List<ProductInfo>>> = _productList

    fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProductList().let {
                _productList.postValue(it)
            }
        }
    }
}