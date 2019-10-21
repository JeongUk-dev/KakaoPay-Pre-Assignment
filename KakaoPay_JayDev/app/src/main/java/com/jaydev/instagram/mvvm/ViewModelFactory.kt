package com.jaydev.instagram.mvvm

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jaydev.instagram.view.main.MainViewModel

class ViewModelFactory private constructor(private val application: Application) :
	ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>) =
		with(modelClass) {
			when {
				isAssignableFrom(MainViewModel::class.java) -> MainViewModel()

				else ->
					throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
			}
		} as T

	companion object {

		@SuppressLint("StaticFieldLeak")
		@Volatile
		private var INSTANCE: ViewModelFactory? = null

		fun getInstance(application: Application) =
			INSTANCE
				?: synchronized(ViewModelFactory::class.java) {
					INSTANCE
						?: ViewModelFactory(application).also { INSTANCE = it }
				}
	}
}
