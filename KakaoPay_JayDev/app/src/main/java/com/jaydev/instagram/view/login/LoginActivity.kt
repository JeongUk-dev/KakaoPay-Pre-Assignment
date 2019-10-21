package com.jaydev.instagram.view.login

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jaydev.instagram.R
import com.jaydev.instagram.base.BaseActivity
import com.jaydev.instagram.common.UserInfoManager
import com.jaydev.instagram.databinding.ActivityLoginBinding
import com.jaydev.instagram.net.InstagramService
import com.jaydev.instagram.util.Logger
import com.jaydev.instagram.util.ext.toast
import com.jaydev.instagram.view.main.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

	private val url = (InstagramService.ROOT_URL
			+ "oauth/authorize/?client_id="
			+ InstagramService.CLIENT_ID
			+ "&redirect_uri="
			+ InstagramService.REDIRECT_URL
			+ "&response_type=token")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		getAccessToken()
	}

	private fun getAccessToken() {

		binding.webView.run{
			settings.run {
				javaScriptEnabled = true
				builtInZoomControls = false
				setGeolocationEnabled(true)
				setAppCacheEnabled(false)
				loadsImagesAutomatically = true
			}

			loadUrl(this@LoginActivity.url)
			webViewClient = object : WebViewClient() {

				var isAuthCompleted = false

				override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
					super.onPageStarted(view, url, favicon)
					Logger.d("$url load start.")
				}

				override fun onPageFinished(view: WebView?, url: String?) {
					super.onPageFinished(view, url)
					Logger.d("loaded page $url")
					url?.takeIf { url.contains("#access_token=") && !isAuthCompleted }?.let { fullUrl ->
						val uri = Uri.parse(fullUrl)
						uri.encodedFragment?.let {
							UserInfoManager.getInstance().accessToken = it.substring(it.lastIndexOf("=") + 1)
						}

						Logger.i("ACCESS_TOKEN : ${UserInfoManager.getInstance().accessToken}")
						isAuthCompleted = true

						startMain()
					}?.takeIf { url.contains("?error") }?.let {
						Logger.e("get accessToken fail")
						toast("accessToken get failed.")
					}
				}
			}
		}
	}

	private fun startMain() {
		val intent = Intent(this, MainActivity::class.java)
		startActivity(intent)
		finish()
	}
}