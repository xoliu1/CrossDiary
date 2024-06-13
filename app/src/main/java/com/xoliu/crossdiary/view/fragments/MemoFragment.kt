package com.xoliu.crossdiary.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.xoliu.crossdiary.R
import com.xoliu.crossdiary.databinding.FragmentMemoBinding
import com.xoliu.crossdiary.network.RetrofitInstance
import com.xoliu.crossdiary.repository.DialogueRepository
import com.xoliu.crossdiary.repository.NetResult
import com.xoliu.crossdiary.view.adapters.DialogueAdapter
import com.xoliu.crossdiary.viewmodel.MemoViewModel
import kotlinx.coroutines.launch


/**
 * Memo fragment
 *
 * @constructor Create empty Memo fragment
 */
class MemoFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentMemoBinding? = null

    private val viewModel: MemoViewModel = MemoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    /**
     * Initialize
     *
     */
    fun initialize() {
        val dialogueRecyclerView = view?.findViewById<RecyclerView>(R.id.dialogueRecyclerView)

        viewModel.dialogues.observe(viewLifecycleOwner) {
            when (it) {
                is NetResult.Success -> {
                    val adapter = DialogueAdapter(mutableListOf())
                    adapter.updateDatas(it.data)
                    dialogueRecyclerView?.adapter = adapter
                    dialogueRecyclerView?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                    initAnim()
                }
                is NetResult.Error -> {
                    Log.e("TAG", "failed", )
                }
            }
        }
        lifecycleScope.launch {
            viewModel.loadDialogues(7)
        }

    }

    /**
     * Init anim
     *
     */
    private fun initAnim() {
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.rv_anim)
        val layoutAnimationController = LayoutAnimationController(animation)
        layoutAnimationController.order = LayoutAnimationController.ORDER_NORMAL
        layoutAnimationController.delay = 0.2f
        binding.dialogueRecyclerView?.setLayoutAnimation(layoutAnimationController)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * On destroy view
     *
     */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}