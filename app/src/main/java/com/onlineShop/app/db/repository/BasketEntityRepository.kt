package com.onlineShop.app.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.onlineShop.app.db.dao.BasketEntityDao
import com.onlineShop.app.db.models.BasketEntity
import com.onlineShop.app.db.OnlineShopDataBase

class BasketEntityRepository(application: Application) {

    private var basketDao: BasketEntityDao
    private var liveDataList: LiveData<List<BasketEntity>>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        basketDao = database.basketDao()
        liveDataList = basketDao.getAllLive()
    }

    suspend fun insert(basket: BasketEntity) {
        basketDao.add(basket)
    }

    suspend fun update(basket: BasketEntity) {
        basketDao.update(basket)
    }

    suspend fun delete(basket: BasketEntity) {
        basketDao.delete(basket)
    }

    suspend fun deleteAll() {
        return basketDao.deleteAll()
    }

    suspend fun getAllBasketList(): List<BasketEntity> {
        return basketDao.getAll()
    }

    fun getAllBasketLive(): LiveData<List<BasketEntity>> {
        return liveDataList
    }

    suspend fun incrementQuantity(basket: BasketEntity) {
        basket.quantity++
        update(basket)
    }

    suspend fun decrementQuantity(basket: BasketEntity) {
        basket.quantity--
        if (basket.quantity <= 0) delete(basket)
        else update(basket)
    }
}