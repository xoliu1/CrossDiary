package com.xoliu.crossdiary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xoliu.crossdiary.model.entities.Dialogue
import com.xoliu.crossdiary.network.RetrofitInstance
import com.xoliu.crossdiary.repository.DialogueRepository
import com.xoliu.crossdiary.repository.NetResult
import kotlinx.coroutines.launch

class MemoViewModel() : ViewModel() {
    private val dialogueRepository: DialogueRepository = DialogueRepository(RetrofitInstance.apiTaiCi)
    private val _dialogues = MutableLiveData<NetResult<List<Dialogue>>>()
    val dialogues: LiveData<NetResult<List<Dialogue>>> = _dialogues

//    fun loadDialogue() {
//        viewModelScope.launch {
//            dialogueRepository.getDialogue().collect { result ->
//                _dialogues.value = result
//                Log.e("TAG", "MemoViewModel -> dialogueRepository.getDialogue().collect： $result", )
//            }
//        }
//    }

    fun loadDialogues(count: Int) {
        viewModelScope.launch {
            val dialoguesList = mutableListOf<Dialogue>()
            repeat(count) {
                dialogueRepository.getDialogue().collect { result ->
                    when (result) {
                        is NetResult.Success -> {
                            dialoguesList.add(result.data)
                            _dialogues.value = NetResult.Success(dialoguesList)
                        }
                        is NetResult.Error -> {
                            _dialogues.value = result
                            return@collect
                        }
                    }
                }
            }
        }
    }
}

