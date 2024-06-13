package com.xoliu.crossdiary.view.adapters


import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.xoliu.crossdiary.R
import com.xoliu.crossdiary.model.entities.Note
import java.time.format.DateTimeFormatter

class NotesAdapter(
    var notes: List<Note>,
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayOfMonthTextView: TextView = itemView.findViewById(R.id.tv_day)
        private val weekDayTextView: TextView = itemView.findViewById(R.id.tv_week_day)
        private val timeTextView: TextView = itemView.findViewById(R.id.tv_time)
        private val fullDateTextView: TextView = itemView.findViewById(R.id.tv_full_date)
        private val contentPreviewTextView: TextView = itemView.findViewById(R.id.tv_content_preview)

        @RequiresApi(Build.VERSION_CODES.O)
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        @RequiresApi(Build.VERSION_CODES.O)
        private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(note: Note) {
            dayOfMonthTextView.text = note.date.dayOfMonth.toString().padStart(2, '0')
            weekDayTextView.text = note.weekDay
            timeTextView.text = note.time.format(timeFormatter)
            fullDateTextView.text = note.date.format(dateFormatter)
            contentPreviewTextView.text = note.contentPreview

            //itemView.setOnClickListener { onNoteClicked(note) }
        }
    }
}

