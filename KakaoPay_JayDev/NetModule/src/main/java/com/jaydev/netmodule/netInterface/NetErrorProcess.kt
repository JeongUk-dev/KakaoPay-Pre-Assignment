package com.jaydev.netmodule.netInterface

import com.jaydev.netmodule.model.NetError
import com.jaydev.netmodule.service.NetCallProcess
import retrofit2.Call

interface NetErrorProcess {
	fun <T> onCommonErrorParse(error: NetError, callback: NetCallback<T>): Boolean
	fun onErrorBehavior(call: Call<*>, retryNetCallProcess: NetCallProcess<*>)

}
