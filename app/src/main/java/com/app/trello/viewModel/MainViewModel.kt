package com.app.trello.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.trello.di.NetworkRepository
import com.app.trello.model.AllTasksResponse
import com.app.trello.model.LoginResponse
import com.app.trello.utils.DataHandler
import com.app.trello.utils.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import java.io.InterruptedIOException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
 class MainViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _getUsers = MutableLiveData<DataHandler<LoginResponse>>()
    private val _getAllPosts = MutableLiveData<DataHandler<AllTasksResponse>>()
    val getUsersLines: LiveData<DataHandler<LoginResponse>> = _getUsers
    val getAllPostsLines: LiveData<DataHandler<AllTasksResponse>> = _getAllPosts

    fun loginData(login: String, password: String) {
        viewModelScope.launch {
            _getUsers.postValue(DataHandler.Loading())
            try {
                val jsonBody = JSONObject()
                jsonBody.put("login", login)
                jsonBody.put("password", password)
                val body = RequestBody.create(
                    "application/json; charset=utf-8".toMediaTypeOrNull(),
                    jsonBody.toString()
                )
                val response = networkRepository.login(body)
                println(response.body())
                _getUsers.postValue(handleResponse(response))
            }
            catch (e: UnknownHostException) {
                _getUsers.postValue(DataHandler.Error("Internetga bog'lanishda xatolik!"))
            } catch (e: InterruptedIOException) {
                _getUsers.postValue(DataHandler.Error("Internetga bog'lanishda xatolik!"))
            } catch (e: Exception) {
                _getUsers.postValue(DataHandler.Error(message = e.toString()))
            }
        }
    }

    fun getAllPosts() {
        viewModelScope.launch {
            _getAllPosts.postValue(DataHandler.Loading())
            try {
                val header: MutableMap<String, Any> = java.util.HashMap()
                header["Authorization"] = "Bearer " + SharedPref.token
                val response = networkRepository.getAllPosts(header)
                println("Postlar olinyapti")
                println(response.body())
                _getAllPosts.postValue(handleAllPostsResponse(response))
            }
            catch (e: UnknownHostException) {
                _getAllPosts.postValue(DataHandler.Error("Internetga bog'lanishda xatolik!"))
            } catch (e: InterruptedIOException) {
                _getAllPosts.postValue(DataHandler.Error("Internetga bog'lanishda xatolik!"))
            } catch (e: Exception) {
                _getAllPosts.postValue(DataHandler.Error(message = e.toString()))
            }
        }
    }

    private fun handleResponse(response: Response<LoginResponse>): DataHandler<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                println(it)
                return DataHandler.Success(it)
            }
        }
        return DataHandler.Error(message = response.errorBody().toString())
    }

    private fun handleAllPostsResponse(response: Response<AllTasksResponse>): DataHandler<AllTasksResponse> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                println(it)
                return DataHandler.Success(it)
            }
        }
        return DataHandler.Error(message = response.errorBody().toString())
    }
}