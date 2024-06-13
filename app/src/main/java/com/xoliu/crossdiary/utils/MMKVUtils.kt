package com.xoliu.crossdiary.utils

import com.tencent.mmkv.MMKV

object MMKVUtils {

    private val mmkv: MMKV = MMKV.defaultMMKV()

    // 保存数据，根据value的类型选择合适的MMKV方法
    fun <T> put(key: String, value: T) {
        when (value) {
            is String -> mmkv.encode(key, value)
            is Int -> mmkv.encode(key, value)
            is Boolean -> mmkv.encode(key, value)
            is Float -> mmkv.encode(key, value)
            is Long -> mmkv.encode(key, value)
            is Double -> mmkv.encode(key, value)
            // 可以继续添加其他类型...
            else -> throw IllegalArgumentException("This type can't be saved into MMKV")
        }
    }

    // 读取数据，返回一个泛型类型，使用时需要指定具体的返回类型
    private inline fun <reified T> get(key: String, defaultValue: T): T = with(mmkv) {
        when (T::class) {
            String::class -> getString(key, defaultValue as String) as T
            Int::class -> getInt(key, defaultValue as Int) as T
            Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
            Float::class -> getFloat(key, defaultValue as Float) as T
            Long::class -> getLong(key, defaultValue as Long) as T
            Double::class -> decodeDouble(key, defaultValue as Double) as T
            // 可以继续添加其他类型...
            else -> throw IllegalArgumentException("This type can't be saved into MMKV")
        }
    }

    // 删除某个key对应的数据
    fun remove(key: String) {
        mmkv.removeValueForKey(key)
    }

    // 查询某个key是否已经存在
    fun contains(key: String): Boolean {
        return mmkv.contains(key)
    }

    // 清除所有数据
    fun clear() {
        mmkv.clearAll()
    }
}
