package tech.misfit.challenge.data.repository

import android.util.Log
import tech.misfit.challenge.data.model.ProductInfo
import tech.misfit.challenge.data.model.StoreInfo
import tech.misfit.challenge.data.network.helper.Resource
import tech.misfit.challenge.data.network.helper.StoreApi
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val api: StoreApi
) : StoreRepository {

    override suspend fun getStoreInfo(): Resource<StoreInfo> {
        Log.v("TAG", "Store Info")
        return try {
            val response = api.getStoreInfo()
            if (response == null) {
                Resource.error("Unknown error", null)
            } else {
                Resource.success(response)
            }
        } catch (exception: Exception) {
            Resource.error("Request failed, please try again later", null)
        }
    }

    override suspend fun getProductList(): Resource<List<ProductInfo>> {
        Log.v("TAG", "Product List")
        return try {
            val response = api.getProductList()
            if (response == null) {
                Resource.error("Unknown error", null)
            } else {
                Resource.success(response)
            }
        } catch (exception: Exception) {
            Resource.error("Request failed, please try again later", null)
        }
    }
}