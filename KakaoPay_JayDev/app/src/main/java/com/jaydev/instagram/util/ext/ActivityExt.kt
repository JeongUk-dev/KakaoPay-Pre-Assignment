package com.jaydev.instagram.util.ext

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.jaydev.instagram.mvvm.ViewModelFactory

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
	ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun Activity.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()