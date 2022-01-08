package tech.misfit.challenge.data.repository

import tech.misfit.challenge.data.model.ProductInfo
import tech.misfit.challenge.data.model.StoreInfo
import tech.misfit.challenge.data.network.helper.Resource

interface StoreRepository {
    suspend fun getStoreInfo(): Resource<StoreInfo>
    suspend fun getProductList(): Resource<List<ProductInfo>>
}