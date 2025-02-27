package com.andrespelaezp.personaltaskplanningapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.andrespelaezp.datasourcecompiler.data.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NetworkMonitor(
    private val context: Context,
    private val repository: TaskRepository
) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun startMonitoring() {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.syncUnsyncedWorkPackages()
                }
            }
        })
    }
}