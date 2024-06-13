package com.xoliu.crossdiary.view.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xoliu.crossdiary.R
import com.xoliu.crossdiary.base.BaseFragment
import com.xoliu.crossdiary.databinding.FragmentMemoBinding
import com.xoliu.crossdiary.viewmodel.MineViewModel

class MineFragment : BaseFragment<FragmentMemoBinding>() {

    companion object {
        fun newInstance() = MineFragment()
    }

    private val viewModel: MineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cross, container, false)
    }



}