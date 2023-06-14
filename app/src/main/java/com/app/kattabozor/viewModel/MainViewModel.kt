package com.app.kattabozor.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.kattabozor.di.NetworkRepository
import com.app.kattabozor.model.Offers
import com.app.kattabozor.model.OffersResponce
import com.app.kattabozor.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import java.io.InterruptedIOException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
 class MainViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _getProducts = MutableLiveData<DataHandler<OffersResponce>>()
    val getProductsLines: LiveData<DataHandler<OffersResponce>> = _getProducts

    fun getAllData() {
        viewModelScope.launch {
            _getProducts.postValue(DataHandler.Loading())
            try {
                val response = networkRepository.getProducts()
                println(response.body())
                _getProducts.postValue(handleResponse(response))
            }
            catch (e: UnknownHostException) {
                _getProducts.postValue(DataHandler.Error("Internetga bog'lanishda xatolik!"))
            } catch (e: InterruptedIOException) {
                _getProducts.postValue(DataHandler.Error("Internetga bog'lanishda xatolik!"))
            } catch (e: Exception) {
                _getProducts.postValue(DataHandler.Error(message = e.toString()))
            }
//            val response = networkRepository.getProducts()
//            Timber.d("response: $response")
//            _getProducts.postValue(handleResponse(response))
        }
    }

    private fun handleResponse(response: Response<OffersResponce>): DataHandler<OffersResponce> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                println(it)
                return DataHandler.Success(it)
            }
        }
        return DataHandler.Error(message = response.errorBody().toString())
    }
}