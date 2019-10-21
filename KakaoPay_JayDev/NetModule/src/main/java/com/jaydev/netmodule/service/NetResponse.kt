package com.jaydev.netmodule.service

import com.jaydev.netmodule.netInterface.NetCallParams

class NetResponse<T> {
	var netCallParams: NetCallParams<*>? = null
	var receiveData: T? = null
}
