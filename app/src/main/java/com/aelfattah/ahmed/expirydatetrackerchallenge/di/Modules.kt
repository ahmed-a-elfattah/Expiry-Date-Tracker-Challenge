package com.aelfattah.ahmed.expirydatetrackerchallenge.di

import android.content.Context
import androidx.room.Room
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.repository.GoodsRepositoryImpl
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.sources.local.GoodsDao
import com.aelfattah.ahmed.expirydatetrackerchallenge.data.sources.local.GoodsDatabase
import com.aelfattah.ahmed.expirydatetrackerchallenge.ui.expiredGoods.ExpiredGoodsViewModel
import com.aelfattah.ahmed.expirydatetrackerchallenge.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    fun provideGoodsDatabase(context: Context) =
        Room.databaseBuilder(context, GoodsDatabase::class.java, "goods_database")
            .fallbackToDestructiveMigration()
            .build()

    fun provideGoodsDao(goodsDatabase: GoodsDatabase) = goodsDatabase.getGoodsDao()

    single { provideGoodsDatabase(context = get()) }
    single { provideGoodsDao(goodsDatabase = get()) }
}

val repositoryModule = module {
    fun provideGoodsRepositoryImpl(goodsDao: GoodsDao) = GoodsRepositoryImpl(goodsDao = goodsDao)
    single { provideGoodsRepositoryImpl(goodsDao = get()) }
}

val viewModelModule = module {
    fun provideMainViewModel(goodsRepositoryImpl: GoodsRepositoryImpl) =
        HomeViewModel(goodsRepositoryImpl = goodsRepositoryImpl)

    fun provideExpiredGoodsViewModel(goodsRepositoryImpl: GoodsRepositoryImpl) =
        ExpiredGoodsViewModel(goodsRepositoryImpl = goodsRepositoryImpl)

    viewModel { provideMainViewModel(goodsRepositoryImpl = get()) }
    viewModel { provideExpiredGoodsViewModel(goodsRepositoryImpl = get()) }
}

val allModules = listOf(databaseModule, repositoryModule, viewModelModule)