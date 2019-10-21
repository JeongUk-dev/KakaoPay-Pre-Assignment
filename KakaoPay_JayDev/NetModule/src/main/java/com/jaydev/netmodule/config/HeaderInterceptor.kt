package com.jaydev.netmodule.config

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

interface HeaderInterceptor : Interceptor {

	val headers: Headers

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request().newBuilder()
			.headers(headers)
			.build()

		return chain.proceed(request)
	}
}