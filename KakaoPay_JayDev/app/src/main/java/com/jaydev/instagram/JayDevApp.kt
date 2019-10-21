package com.jaydev.instagram

import android.app.Application
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.jaydev.instagram.common.UserInfoManager
import com.jaydev.instagram.net.InstagramService
import com.jaydev.instagram.util.Logger
import com.jaydev.netmodule.config.HeaderInterceptor
import com.jaydev.netmodule.config.NetServiceConfig
import com.jaydev.netmodule.mgr.NetServiceManager
import com.jaydev.netmodule.netInterface.NetFailProcess
import com.jaydev.netmodule.service.NetService
import okhttp3.Authenticator
import okhttp3.Headers
import retrofit2.Call
import java.io.IOException

class JayDevApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setDebuggable(this)
        UserInfoManager.getInstance().init(this)

        initNet()
    }

    private fun initNet() {
        val gson = GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create()

        NetServiceManager.getInstance().netServiceConfig = NetServiceConfig.build(InstagramService.ROOT_URL, gson) {
            isNetDebugMode = true

//            defaultHeader = object : HeaderInterceptor {
//                override val headers: Headers
//                    get() = Headers.Builder().add("Content-Type", "application/json").add("Authorization", "Bearer ${UserInfoManager.getInstance().accessToken}").build()
//            }
//
//            //region refresh accessToken login
//            defaultAuthenticator = Authenticator { _, response ->
//                if (!response.isSuccessful) { // API 통신 실패
//                    val tokenApi = NetService.createNetService(InstagramService.Api::class.java)
//
//                    val param = mapOf(
//                        "userId" to UserInfoManager.getInstance().userId,
//                        "password" to UserInfoManager.getInstance().userPassword,
//                    )
//                    try {
//                        val responseToken = tokenApi.login(param).execute()
//                        if (responseToken.isSuccessful) { // 토큰 인증 성공
//                            val loginSession = responseToken.body()!!
//
//                            UserInfoManager.getInstance().accessToken = loginSession.token
//
//                            val headers = defaultHeader?.headers!!
//
//                            return@Authenticator response.request().newBuilder()
//                                .headers(headers)
//                                .build()
//                        } else { // 토근 인증 실패
//                            return@Authenticator responseToken.raw().request()
//                        }
//                    } catch (e: IOException) {
//                        return@Authenticator null
//                    }
//
//                } else { // 통신 성공
//                    return@Authenticator null
//                }
//            }
//            //endregion

            netFailProcess = object : NetFailProcess {
                override fun onNetErrorBehavior(call: Call<*>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "네트워크 오류", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}