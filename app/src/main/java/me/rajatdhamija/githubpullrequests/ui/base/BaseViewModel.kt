package me.rajatdhamija.githubpullrequests.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.rajatdhamija.githubpullrequests.R
import me.rajatdhamija.githubpullrequests.utils.common.Resource
import me.rajatdhamija.githubpullrequests.utils.network.NetworkHelper
import javax.net.ssl.HttpsURLConnection


abstract class BaseViewModel(
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()
    val logoutUser : MutableLiveData<Boolean> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(Resource.error(R.string.network_default_error))
                    0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    HttpsURLConnection.HTTP_UNAUTHORIZED,403 -> {
                        forcedLogoutUser()
//                        messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                    else -> messageString.postValue(Resource.error(message))
                }
            }
        }

    protected open fun forcedLogoutUser() {
        logoutUser.postValue(true)
    }

    abstract fun onCreate()
}