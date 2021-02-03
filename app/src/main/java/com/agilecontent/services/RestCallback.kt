package com.agilecontent.services

interface RestCallback<T, K> {

    fun onSuccess(t: T)
    fun onError(k: K)

}