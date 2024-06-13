package com.xoliu.crossdiary.model.entities

data class Dialogue(
    val code: Int,
    val msg: String,
    val result: Result
)

data class Result(
    val dialogue: String,
    val english: String,
    val source: String,
    val type: Int
)
/*
 * 公共参数指所有接口都会返回的参数，应用参数每个接口都不同

名称	类型	示例值	说明
code	int	    200	    状态码
msg	    string	success	错误信息
result	object	{}	    返回结果集

dialogue	string	你不要对我这么好，你对我这么好、要是有一天你对我不好了，我会很伤心。	台词
english	string	Don’t be so nice to me. You are so nice to me. If one day you are not nice to me, I will be very sad.	英文
source	string	夏日的么么茶	来源影视剧
type	int	1	台词类型，0外语、1华语
 **/
