package com.xoliu.crossdiary.base

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

// 定义一个泛型类型T，它是ViewBinding的子类型
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    // 延迟初始化ViewBinding
    private lateinit var _binding: T
    // 只读属性，供子类访问
    protected val binding get() = _binding

    // 抽象方法，由子类实现以提供ViewBinding
    abstract fun getViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 从抽象方法获取ViewBinding实例
        _binding = getViewBinding()
        // 设置视图内容为ViewBinding提供的根视图
        setContentView(_binding.root)

        // 调用初始化方法
        setup()

    }

    // 子类可以重写这个方法来完成它们的初始化工作
    open fun setup() {
        // 默认实现为空
        // 沉浸效果
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    override fun onDestroy() {
        super.onDestroy()
        // 清除对ViewBinding的引用

    }
}