package com.example.myapplication;

/**
 * Created by adaJQD on 2017/1/12.
 */
public class Config {
    //需要适配的设备配置 宽度
    public final static int[] supportDevices = {300, 320, 340, 360, 370, 400, 420, 450, 480, 520, 600, 620, 800};
    //已知文件对应屏幕宽度
	public final static int EXISTS_SMALL_WIDTH = 320;
    //values 文件夹路径 。默认在此路径下存放dimens.xml
    public final static String path = "src/dimens/dimens.xml";//绝对文件地址
}

