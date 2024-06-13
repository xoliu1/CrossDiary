package com.xoliu.crossdiary.view.fragments


import android.os.Bundle

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.xoliu.crossdiary.base.BaseFragment
import com.xoliu.crossdiary.databinding.FragmentNoteBinding

import com.xoliu.crossdiary.model.local.database.AppDatabase
import com.xoliu.crossdiary.view.adapters.NotesAdapter
import com.xoliu.crossdiary.view.dialog.AddNoteBottomSheetDialogFragment
import com.xoliu.crossdiary.viewmodel.NoteViewModel


class NoteFragment : BaseFragment<FragmentNoteBinding>() {

    private val notesAdapter: NotesAdapter by lazy { NotesAdapter(listOf()) }
    private val viewModel: NoteViewModel by viewModels<NoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupAddNoteButton()
        observeNotes()
    }

    private fun setupRecyclerView() {
        binding.noteRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }
    }

    private fun setupAddNoteButton() {
        binding.btnAddNote.setOnClickListener {
            val addNoteDialogFragment = AddNoteBottomSheetDialogFragment()
            addNoteDialogFragment.show(parentFragmentManager, addNoteDialogFragment.tag)
        }
    }

    private fun observeNotes() {
        viewModel.getNoteDao(requireContext()).observe(viewLifecycleOwner) { notes ->
            notes?.let {
                binding.nullTexts.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                notesAdapter.notes = it
                notesAdapter.notifyDataSetChanged()
            }
        }
    }
}

