package com.jaydev.instagram.net

import com.jaydev.instagram.data.PostsResponse
import com.jaydev.instagram.data.UserProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

object InstagramService {

	val ROOT_URL = "https://api.instagram.com/"

	val CLIENT_ID = "6e4f7b544aa74f0ca3602b5859625f34"
	val REDIRECT_URL = "http://localhost"

	interface Api {
		@GET("v1/users/self/")
		fun getMyInfo(@QueryMap param: HashMap<String, Any>): Call<UserProfileResponse>

		@GET("v1/users/self/media/recent/")
		fun getMyPostList(@QueryMap param: HashMap<String, Any>): Call<PostsResponse>

//		@POST("v1/media/{media-id}/likes")
//		fun setLike(@Path("media-id") postId: String, @QueryMap param: HashMap<String, Any>): Call<Unit>
//
//		@GET("v1/media/{media-id}/likes")
//		fun getLike(@Path("media-id") postId: String, @QueryMap param: HashMap<String, Any>): Call<Unit>
	}
}