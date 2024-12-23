package com.onlineShop.app.viewmodels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onlineShop.app.models.ServiceResponse
import com.onlineShop.app.models.products.ProductCategory
import com.onlineShop.app.repositories.products.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(
    private val repository: ProductCategoryRepository
) : ViewModel() {

    var dataList = mutableStateOf<List<ProductCategory>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getCategories { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getCategories(onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getCategory()
            onResponse(response)
        }
    }

    fun getCategoryById(
        id: Long,
        onResponse: (response: ServiceResponse<ProductCategory>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getProductsByCategoryId(id)
            onResponse(response)
        }
    }
}