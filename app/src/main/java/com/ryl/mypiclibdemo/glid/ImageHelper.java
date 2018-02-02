//package com.ryl.mypiclibdemo.glid;
//
//import android.content.Context;
//import android.support.annotation.Nullable;
//import android.widget.ImageView;
//
//import com.bumptech.glide.request.RequestOptions;
//
///**
// * 添加接口定义,可以方便更换图片库
// * <p>
// * 所有请求必须可以取消,不然可能造成内存泄露,或刷界面的时候页面已经被回收,导致空指针问题
// * Created by rongyile on 2018/1/26.
// */
//
//public interface ImageHelper {
//
//    /**
//     * 位置
//     */
//
//    //加载图片(网络地址和本地地址一样的方法)
//    public void loadPic(Context context, String uri, ImageView view);
//
//    void loadPic(Context context, String uri, ImageView view, RequestOptions options);
//
//
//    //应用默认图片目录
//
//    /***
//     *
//     * @param context
//     * @param parentPath parentPath 必须是带"/"的文件夹目录
//     * @param picName
//     * @param view
//     */
//    void getLocalPicByName(Context context, String parentPath, String picName, ImageView view);
//
//    //加载 SD卡文件,绝对路径,需要提前验证权限(动态权限)
//    void getLocalPicByPath(Context context, String picName, ImageView view);
//
//
//    //网络获取的直接文件,需要解码等
////    ImageHelper getStreamPic( String pic);
//
//    //清理磁盘缓存
////    void clearDiskCache(Context context);//(耗时任务,需要开线程) Glide.get(applicationContext).clearDiskCache();
//
//
//    //清理内存缓存
////void clearMemoryCache(Context context);t
//
//    /**
//     * 图片处理
//     */
//
//    RequestOptions getDefaultOptions(@Nullable RequestOptions options);
//
//    //圆形图片
//    RequestOptions getRoundOptions(@Nullable RequestOptions options);
//
//    //    接入glide-transformations
//    RequestOptions getSepiaOptions(@Nullable RequestOptions options);
//
//
//    RequestOptions getNoDiskCacheOptions(@Nullable RequestOptions options);    //    .skipMemoryCache(true)
//
//    RequestOptions getNoCacheOptions(@Nullable RequestOptions options);//禁用内存缓存和 sd卡缓存//     .diskCacheStrategy(DiskCacheStrategy.NONE)
//
//
//}
//
