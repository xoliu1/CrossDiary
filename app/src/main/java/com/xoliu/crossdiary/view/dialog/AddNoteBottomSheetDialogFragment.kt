package com.xoliu.crossdiary.view.dialog

import android.app.Application
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xoliu.crossdiary.R
import com.xoliu.crossdiary.databinding.DialogAddNoteBinding
import com.xoliu.crossdiary.model.entities.Note
import com.xoliu.crossdiary.model.local.database.AppDatabase
import com.xoliu.crossdiary.repository.NoteRepository
import com.xoliu.crossdiary.viewmodel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale

class AddNoteBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var etNoteContent: EditText

    private var _binding: DialogAddNoteBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = DialogAddNoteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java) // 获取Activity的ViewModel
        etNoteContent = binding.etNoteContent

        binding.btnSubmitNote.setOnClickListener {
            submitNote()
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun submitNote() {
        val content = etNoteContent.text.toString().trim()
        if (content.isNotEmpty()) {
            val currentDate = LocalDate.now()
            val currentTime = LocalTime.now()
            val newNote = Note(
                date = currentDate,
                weekDay = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                time = currentTime,
                contentPreview = content.take(100), // 取内容前100个字符作为预览
                fullContent = content
            )
//            //viewModel.insert(newNote, requireActivity().application)
//            GlobalScope.launch(Dispatchers.IO) {
//                NoteRepository(requireActivity().application).insert(newNote)
//            }
            insertNote(newNote)
            dismiss()
            Toast.makeText(context, "记录成功", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "不能留白", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertNote(note: Note) {
        val noteDao = AppDatabase.getDatabase(requireContext()).noteDao()
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insert(note)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}
