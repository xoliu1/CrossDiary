package com.xoliu.crossdiary.repository


// 封装数据响应的结果状态
sealed class NetResult<out T> {
    data class Success<out T>(val data: T) : NetResult<T>()
    data class Error(val message: String, val exception: Exception? = null) : NetResult<Nothing>()
}