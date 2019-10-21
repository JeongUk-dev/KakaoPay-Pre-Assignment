package com.jaydev.netmodule.netInterface

import retrofit2.Call

interface NetFailProcess {
	fun onNetErrorBehavior(call: Call<*>, t: Throwable)
}
