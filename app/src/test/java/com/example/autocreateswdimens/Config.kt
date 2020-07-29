package com.example.autocreateswdimens

/**
 * Created by adaJQD on 2017/1/12.
 */
object Config {
    //需要适配的设备宽度，单位：dp
    val supportDevices = intArrayOf(240, 300, 340, 360, 370, 400, 420, 450, 480, 520, 600, 620, 640, 800)

    //已知文件对应屏幕宽度，单位：dp
    const val DEFAULT_DIMEN_WIDTH = 640
    //生成文件目录
    const val GENERATE_DIMEN_FILE_DIR = "/Users/sonmao/workspace/projectSource/cxda/svn/01.cxdaCode/01.Vehicle/app/ops/app/src/main/res/"
    //默认值文件
    const val DEFAULT_DIMEN_FILE_PATH = GENERATE_DIMEN_FILE_DIR.plus("values/dimens.xml")
}