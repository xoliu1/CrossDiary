package com.xoliu.crossdiary.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xoliu.crossdiary.R
import com.xoliu.crossdiary.model.entities.Dialogue

class DialogueAdapter(private var dialogues: MutableList<Dialogue>) : RecyclerView.Adapter<DialogueAdapter.DialogueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_dialogue_item, parent, false)
        return DialogueViewHolder(view)
    }

    fun updateData(newDialogue: Dialogue) {
        this.dialogues.add(newDialogue)
    }

    fun updateDatas(newDialogues: List<Dialogue>){
        this.dialogues.addAll(newDialogues)
    }
    fun changeDatas(newDialogues: MutableList<Dialogue>){
        this.dialogues.clear()
        this.dialogues.addAll(newDialogues)
    }

    override fun onBindViewHolder(holder: DialogueViewHolder, position: Int) {
        val dialogue = dialogues[position].result
        holder.tvDialogue.text = dialogue.dialogue
        holder.tvEnglish.text = dialogue.english
        holder.tvSource.text = dialogue.source

        // 根据type显示不同文本
        when (dialogue.type) {
            0 -> holder.tvEnglish.text = dialogue.english
            1 -> holder.tvEnglish.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return dialogues.size
    }

    class DialogueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDialogue: TextView = itemView.findViewById(R.id.tvDialogue)
        val tvEnglish: TextView = itemView.findViewById(R.id.tvEnglish)
        val tvSource: TextView = itemView.findViewById(R.id.tvSource)

    }
}