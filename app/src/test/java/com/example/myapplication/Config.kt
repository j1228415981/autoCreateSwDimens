package com.example.myapplication

/**
 * Created by adaJQD on 2017/1/12.
 */
object Config {
    //需要适配的设备配置 宽度
    val supportDevices =
        intArrayOf(300, 320, 340, 360, 370, 400, 420, 450, 480, 520, 600, 620, 800)

    //已知文件对应屏幕宽度
    const val EXISTS_SMALL_WIDTH = 320

    //values 文件夹路径 。默认在此路径下存放dimens.xml
    const val path = "src/dimens/dimens.xml" //绝对文件地址
}