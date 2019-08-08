package com.sun.component.commonres.entity

data class BaseBean<T> constructor(var data: T, var errorCode: Int, var errorMsg: String)