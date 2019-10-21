package com.jaydev.instagram.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaydev.instagram.common.UserInfoManager
import com.jaydev.instagram.data.Post
import com.jaydev.instagram.data.PostsResponse
import com.jaydev.instagram.data.UserProfile
import com.jaydev.instagram.data.UserProfileResponse
import com.jaydev.instagram.mvvm.Event
import com.jaydev.instagram.net.InstagramService
import com.jaydev.instagram.util.Logger
import com.jaydev.netmodule.mgr.NetMgr
import com.jaydev.netmodule.model.NetError
import com.jaydev.netmodule.netInterface.NetCallback
import com.jaydev.netmodule.service.NetResponse
import com.jaydev.netmodule.service.NetService

class MainViewModel : ViewModel() {

	private val _userProfile = MutableLiveData<UserProfile>()
	val userProfile: LiveData<UserProfile> = _userProfile

	private val _postList = MutableLiveData<List<Post>>()
	val postList: LiveData<List<Post>> = _postList

	val postClickEvent = MutableLiveData<Event<Post>>()

	private val _netErrorDialogEvent = MutableLiveData<Event<String>>()
	val netErrorDialog = _netErrorDialogEvent

	fun getMyInfo() {
		val netService = NetService.createConfiguredNetService(InstagramService.Api::class.java)

		val param: HashMap<String, Any> = hashMapOf("access_token" to UserInfoManager.getInstance().accessToken)

		val call = netService.getMyInfo(param)
		NetMgr.request(call, object : NetCallback<NetResponse<UserProfileResponse>> {
			override fun onSuccResponse(responseData: NetResponse<UserProfileResponse>) {
				Logger.d("responseData = ${responseData.receiveData}")

				_userProfile.value = responseData.receiveData?.data
			}

			override fun onFailResponse(error: NetError, responseData: NetResponse<UserProfileResponse>) {
				Logger.d("getMyInfo fail")
				_netErrorDialogEvent.value = Event("${error.code}\n${error.message}")

			}

			override fun clone(): NetCallback<NetResponse<UserProfileResponse>> {
				return this
			}
		})

	}

	fun getMyPostList() {
		val netService = NetService.createConfiguredNetService(InstagramService.Api::class.java)

		val param: HashMap<String, Any> = hashMapOf("access_token" to UserInfoManager.getInstance().accessToken)

		val call = netService.getMyPostList(param)
		NetMgr.request(call, object : NetCallback<NetResponse<PostsResponse>> {
			override fun onSuccResponse(responseData: NetResponse<PostsResponse>) {
				Logger.d("responseData = ${responseData.receiveData}")

				_postList.value = responseData.receiveData?.data
			}

			override fun onFailResponse(error: NetError, responseData: NetResponse<PostsResponse>) {
				Logger.d("getMyPostList fail")
				_netErrorDialogEvent.value = Event("${error.code}\n${error.message}")
			}

			override fun clone(): NetCallback<NetResponse<PostsResponse>> {
				return this
			}
		})
	}



}