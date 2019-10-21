package com.jaydev.netmodule.netInterface

import com.jaydev.netmodule.model.NetError

interface NetCallback<T> : Cloneable {
	fun onSuccResponse(responseData: T)
	fun onFailResponse(error: NetError, responseData: T)

	public override fun clone(): NetCallback<T>

}
