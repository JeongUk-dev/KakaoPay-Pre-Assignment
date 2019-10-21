package com.jaydev.netmodule.service

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.jaydev.netmodule.mgr.NetServiceManager
import okhttp3.Authenticator
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object NetService {

	private class PrettyPrintLogger : HttpLoggingInterceptor.Logger {
		override fun log(message: String) {
			val logName = "OkHttp"
			if (message.startsWith("{") || message.startsWith("[")) {
				try {
					val prettyPrintJson = GsonBuilder().setPrettyPrinting().create().toJson(JsonParser().parse(message))
					Log.d(logName, prettyPrintJson)
				} catch (m: JsonSyntaxException) {
					Log.d(logName, message)
				}
			} else {
				Log.d(logName, message)
				return
			}
		}
	}

	fun <T> createNetService(netTarget: Class<T>): T {
		if (NetServiceManager.getInstance().netServiceConfig == null) {
			throw IllegalAccessError("You must configuration NetServiceConfig into NetServiceManager.")
		}

		val netServiceConfig = NetServiceManager.getInstance().netServiceConfig!!

		val httpClient = OkHttpClient().newBuilder()
		val builder = Retrofit.Builder()
				.baseUrl(netServiceConfig.baseURL)
				.addConverterFactory(GsonConverterFactory.create(netServiceConfig.gson))

		if (netServiceConfig.isNetDebugMode) {
			val loggingInterceptor = HttpLoggingInterceptor(PrettyPrintLogger())
			loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

			httpClient.addInterceptor(loggingInterceptor)
		}

		builder.client(httpClient.build())

		return builder.build().create(netTarget)
	}

	fun <T> createConfiguredNetService(netTarget: Class<T>, authenticator: Authenticator? = null, vararg interceptor: Interceptor): T {
		if (NetServiceManager.getInstance().netServiceConfig == null) {
			throw IllegalAccessError("You must configuration NetServiceConfig into NetServiceManager.")
		}

		val netServiceConfig = NetServiceManager.getInstance().netServiceConfig!!

		val httpClient = OkHttpClient().newBuilder()
		val builder = Retrofit.Builder()
				.baseUrl(netServiceConfig.baseURL)
				.addConverterFactory(GsonConverterFactory.create(netServiceConfig.gson))

		if (netServiceConfig.isNetDebugMode) {
			val loggingInterceptor = HttpLoggingInterceptor(PrettyPrintLogger())
			loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

			httpClient.addInterceptor(loggingInterceptor)
		}

		netServiceConfig.defaultAuthenticator?.let {
			httpClient.authenticator(it)
		}

		authenticator?.let {
			httpClient.authenticator(it)
		}

		netServiceConfig.defaultHeader?.let {
			httpClient.addInterceptor(it)
		}

		interceptor.forEach {
			httpClient.addInterceptor(it)
		}

		httpClient.connectionPool(
				ConnectionPool(netServiceConfig.connectionPoolSize, netServiceConfig.timeOutSec, TimeUnit.SECONDS)
		)
		httpClient.readTimeout(netServiceConfig.timeOutSec, TimeUnit.SECONDS)
		httpClient.connectTimeout(netServiceConfig.timeOutSec, TimeUnit.SECONDS)

		builder.client(httpClient.build())

		return builder.build().create(netTarget)

	}

	/**
	 * php 통신 json 파라미터 생성
	 */
	fun createJsonParams(params: Any): JsonObject {
		val paramMap = NetServiceManager.getInstance().netServiceConfig?.defaultParams?.configDefaultParams()

		val gson = Gson()

		val jsonStr = Gson().toJson(params)
		val typeOfHashMap = object : TypeToken<Map<String, Any>>() {}.type
		val newMap = Gson().fromJson<HashMap<String, Any>>(jsonStr, typeOfHashMap)
		paramMap?.let { newMap.putAll(it) }

		return JsonParser().parse(gson.toJson(newMap)).asJsonObject

	}

	/**
	 * php 통신 json 파라미터 생성
	 */
	fun createDefaultJsonParams(): JsonObject {
		val paramMap = NetServiceManager.getInstance().netServiceConfig?.defaultParams?.configDefaultParams()

		val gson = Gson()
		return JsonParser().parse(gson.toJson(paramMap)).asJsonObject

	}

	fun isNetworkConnected(context: Context): Boolean {
		val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

		val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

		val activeNetwork = cm.activeNetworkInfo

		return activeNetwork != null && activeNetwork.isConnectedOrConnecting

	}

}
