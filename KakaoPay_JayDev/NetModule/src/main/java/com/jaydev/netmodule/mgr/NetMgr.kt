package com.jaydev.netmodule.mgr

import com.jaydev.netmodule.netInterface.NetCallParams
import com.jaydev.netmodule.netInterface.NetCallback
import com.jaydev.netmodule.service.NetCallProcess
import com.jaydev.netmodule.service.NetResponse
import retrofit2.Call

object NetMgr {
	fun <T> request(call: Call<T>, callback: NetCallback<NetResponse<T>>) {
		call.enqueue(NetCallProcess(callback))
	}

	fun <T> request(call: Call<T>, callback: NetCallback<NetResponse<T>>, netCallParams: NetCallParams<*>) {
		call.enqueue(NetCallProcess(callback, netCallParams))
	}
}
