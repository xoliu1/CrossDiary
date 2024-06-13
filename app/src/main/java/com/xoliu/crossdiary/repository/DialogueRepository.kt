package com.xoliu.crossdiary.repository
import com.xoliu.crossdiary.Constant
import com.xoliu.crossdiary.model.entities.Dialogue
import com.xoliu.crossdiary.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class DialogueRepository(private val apiService: ApiService) {

    fun getDialogue(): Flow<NetResult<Dialogue>> = flow {
        try {
            val response = apiService.getDialogue(Constant.API_KEY)
            emit(NetResult.Success(response)) // 发射成功结果
        } catch (e: Exception) {
            emit(NetResult.Error(e.message ?: "未知错误", e)) // 发射错误结果
        }
    }.flowOn(Dispatchers.IO) // 在IO调度器中执行


}

