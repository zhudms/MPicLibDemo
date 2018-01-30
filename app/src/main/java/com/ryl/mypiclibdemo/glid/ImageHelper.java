package com.ryl.mypiclibdemo.glid;

/**
 *
 * 添加接口定义,可以方便更换图片库
 *
 * 所有请求必须可以取消,不然可能造成内存泄露,或刷界面的时候页面已经被回收,导致空指针问题
 * Created by rongyile on 2018/1/26.
 */
public interface ImageHelper {

    /**
     * 位置
     */

    //网络地址(最常用)
    ImageHelper getNetPic(String uri);


    //本地文件

    //完整路径
    ImageHelper getLocalPic(String picPath);

    //应用默认图片目录
    ImageHelper getLocalPicByName(String picName);


    //网络获取的直接文件,需要解码等
    ImageHelper getStreamPic( String pic);


    /**
     * 形状
     */
    ImageHelper getRoundPic();


    /**
     * SD缓存
     */
    ImageHelper storagePIc();

    /**
     * 图片处理
     */

}
