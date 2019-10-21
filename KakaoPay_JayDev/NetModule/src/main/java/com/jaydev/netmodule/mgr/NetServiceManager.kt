package com.jaydev.netmodule.mgr

import com.jaydev.netmodule.config.NetServiceConfig


class NetServiceManager private constructor() {

	var netServiceConfig: NetServiceConfig? = null

	companion object {
		private var instance: NetServiceManager? = null

		@JvmStatic
		fun getInstance() = instance ?: synchronized(this) {
			instance ?: NetServiceManager().also { instance = it }
		}
	}
}
