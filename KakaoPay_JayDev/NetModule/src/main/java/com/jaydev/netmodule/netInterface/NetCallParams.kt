package com.jaydev.netmodule.netInterface

/**
 * php 통신 파라미터.
 */
interface NetCallParams<T> : Cloneable {
	val params: T

	public override fun clone(): NetCallParams<T>
}
