package com.ryl.mypiclibdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ryl.commonlib.utils.PermissionResult;
import com.ryl.commonlib.utils.PermissionU;
import com.ryl.commonlib.utils.ToastU;
import com.ryl.commonlib.utils.WindowU;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity {


    private RecyclerView view;
    private ImageView mImageView;

    private String[] s = {

            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517380932213&di=785e9f46d9d09d022ada1dfa2f73a34c&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201411%2F04%2F20141104171337_xaMXx.jpeg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517380932213&di=f1998db477ff095a733cdb43f7d17843&imgtype=0&src=http%3A%2F%2Fimg.bbs.cnhubei.com%2Fforum%2Fdvbbs%2F2004-4%2F200441915031894.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1694240432,3664501847&fm=214&gp=0.jpg"
            , "http://img4.imgtn.bdimg.com/it/u=2353945989,2522924748&fm=214&gp=0.jpg"
            ,
            "http://p1.music.126.net/1DQ7buwuBml2cfa4Prwyig==/109951163091218734.jpg"
            , "http://p1.music.126.net/I0BPzraIsUChOs4-Q4CQzA==/18981968742450744.jpg"
            , "http://p1.music.126.net/MGIjNwzVwcquv9zjfsdPKQ==/109951163118174516.jpg"
            , "http://p1.music.126.net/9z7m8ilB6aK3fmCyqAUXzg==/109951163116581851.jpg"
            , "http://p1.music.126.net/YaAIDPETpDz9-phHiMEDhw==/109951163117041486.jpg"
            , "http://p1.music.126.net/wfNLW9D-XgbCniprlSaxJw==/109951163114453516.jpg"
            , "http://p1.music.126.net/gAinFu7LGE1M1sxWnZEarg==/19052337486656549.jpg"
            , "http://p1.music.126.net/Onile_LdPHd_8N8Cc10zuw==/18603736744098421.jpg"
            , "http://p1.music.126.net/l4n8Zis1dyMYfcCSjfA_SQ==/109951163103475324.jpg"
            , "http://p1.music.126.net/swmc67IAF_n7WoC0bAfoGQ==/109951163108361940.jpg"
            , "http://p1.music.126.net/Jd4ozp4099okTavdjmf9MQ==/109951163097839596.jpg"
            , "http://p1.music.126.net/GoU0psq_83Cgbfs4VKvS6Q==/109951163097836122.jpg"
            , "http://p1.music.126.net/J68_emBsYOmLBTxzcmvF4Q==/109951163106891784.jpg"
            , "http://p1.music.126.net/RK1bf16ijtvRKXDP8Z0QJg==/109951163100674201.jpg"
            , "http://p1.music.126.net/jBeKsmgKrFV2hNSTIdR51g==/109951163099598829.jpg"
            , "http://p1.music.126.net/3HmTNWeARSyRtq2Lyn-98w==/19199672044783860.jpg"
            , "http://p1.music.126.net/dr7_NVCbKRPhaZpG7c97sQ==/109951163098238240.jpg"
            , "http://p1.music.126.net/0oFXqUiqLWZNgl6JvXDYoQ==/109951163107109361.jpg"
            , "http://p1.music.126.net/SBra879KiJAf0ONin5-73w==/109951163110139042.jpg"
            , "http://p1.music.126.net/q0d_SCa1eaT_SKOq7X-2qg==/19056735533180513.jpg"
            , "http://p1.music.126.net/5DTZrJcTO15FwcDL6tI2dg==/109951163101601184.jpg"

    };
    private String pic1 = "http://img.taopic.com/uploads/allimg/140729/240450-140HZP45790.jpg";
    private String pic2 = "http://p1.music.126.net/YG3mvsiivI9TQUw6YNscjg==/19176582300597176.jpg";
    private String pic3 = "http://p1.music.126.net/c0Hl8H-gdr_y1EEdifC4Xw==/19049038951751146.jpg";

    private String[] permissions = {PermissionU.SD_READ, PermissionU.SD_WRITE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initPermission();
        initRecycleView();
//        showSingleImage();


//        initLocalData();


    }

    private void initPermission() {
        PermissionU.checkPermissions(MainActivity.this, permissions, 3);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionResult.PermissionRequestResult result = PermissionU.isSuccess(grantResults, permissions);
        if (result.isSuccess) {

        }

    }

    private void initLocalData() {
        String path = getExternalCacheDir()
                + "/a.jpg";

        File file = new File(path);
        if (file.exists()) {
            ToastU.ht(MainActivity.this, "exit,size=" + file.getTotalSpace());
            mImageView = ((ImageView) findViewById(R.id.main_image));
            Glide.with(MainActivity.this).load(file).into(mImageView);
        }
    }


    private void initRecycleView() {
        view = ((RecyclerView) findViewById(R.id.main_rv));
//        showSingleImage();


        ArrayList<String> list = initData();
        ImagePicAdapter mRecyclerViewAdapter = new ImagePicAdapter(list, MainActivity.this);
        view.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        // 滚动静止时才加载图片资源，提升流畅度(实测与 glide 结合使用不好,不加更好)
//        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == SCROLL_STATE_IDLE) {
//                    mRecyclerViewAdapter.setScrolling(false);
//                    mRecyclerViewAdapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
//                } else
//                    mRecyclerViewAdapter.setScrolling(true);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });

        view.setAdapter(mRecyclerViewAdapter);
    }

    private ArrayList<String> initData() {

        ArrayList<String> list = new ArrayList<>(50);
        list.add(pic1);
        list.add(pic2);
        list.add(pic3);
//        list.add(getExternalCacheDir()
//                + "/a.jpg");
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        return list;
    }


    private void showSingleImage() {
        mImageView = ((ImageView) findViewById(R.id.main_image));
//        GlideBuilder builder = new GlideBuilder();
//        builder.setDiskCache(
//                new InternalCacheDiskCacheFactory(MainActivity.this,
//                        "ryl", 100 * 1024 * 1024));
//init(MainActivity.this,builder).
        Glide.with(MainActivity.this).load(pic1).into(mImageView);
    }


}
