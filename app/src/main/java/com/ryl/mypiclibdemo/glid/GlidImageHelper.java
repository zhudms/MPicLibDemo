package com.ryl.mypiclibdemo.glid;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ryl.commonlib.utils.FileU;
import com.ryl.mypiclibdemo.R;

import java.util.ArrayList;
import java.util.Collection;

import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;


/**
 * 所有图像变换方法都使用glide-transformations中的变换类
 * Created by rongyile on 2018/1/29.
 */

public class GlidImageHelper implements ImageHelper {

    public static final int SIZE_UNDEFINED = -1;

    public RequestOptions options;
    private Collection<Transformation> transformations;


    public GlidImageHelper() {
        transformations = new ArrayList<Transformation>();
        options = getDefaultOptions(null);
    }


    @Override
    public RequestOptions getOptions() {

        options = getDefaultOptions(options);

        if (transformations.size() > 0) {
            MultiTransformation transformation =
                    new MultiTransformation(transformations);
            options.transform(transformation);
        }

        return options;
    }


    @Override
    public GlidImageHelper noCache() {

        options.skipMemoryCache(true);

        return this;
    }


    @Override
    public GlidImageHelper round() {

//        transformations.add(new CircleCrop());
        transformations.add(new CircleCrop());

        return this;
    }


    @Override
    public GlidImageHelper sepia() {

//        options.transform(new SepiaFilterTransformation());
        transformations.add(new SepiaFilterTransformation());

        return this;
    }


    @Override
    public GlidImageHelper toon() {

//        options.transform(new SepiaFilterTransformation());
        transformations.add(new ToonFilterTransformation());

        return this;
    }


    @Override
    public GlidImageHelper noDiskCache() {

        options.diskCacheStrategy(DiskCacheStrategy.NONE);
        return this;
    }


    @Override
    public GlidImageHelper noMemoryCache() {
        options.skipMemoryCache(true);
        return this;
    }


    @Override
    public GlidImageHelper tagSize(int x, int y) {
        if (x > 0 && y > 0) {

            options.override(x, y);
        } else if (x > 0) {
            options.override(x);
        } else {
            getDefaultOptions(options);
        }
        return this;
    }


    @Override
    public  void loadOriginal(Context context, String tag, ImageView view) {
        Glide.with(context).load(tag).into(view);
    }


    //最基础的方法
    @Override
    public void loadPic(Context context, String uri
            , ImageView view, @Nullable RequestOptions options) {
        if (options == null) {
            Glide.with(context).load(uri).apply(getDefaultOptions(null)).into(view);
        } else {
            Glide.with(context).load(uri).apply(options).into(view);
        }
    }


    @Override
    public void getLocalPicByName(Context context, String parentPath
            , String picName, ImageView view, @Nullable RequestOptions options) {

        if (view == null) {
            return;
        }

        String path;

        if (FileU.isFloder(parentPath)) {
            path = new StringBuffer(parentPath).append(picName).toString();
            loadPic(context, path, view, options);
        } else {
            return;
        }

    }


    @Override
    public void getLocalPicByPath(Context context, String picPath
            , ImageView view, RequestOptions options) {
        if (view == null) {
            return;
        }

        if (FileU.isFileExits(picPath)) {
            loadPic(context, picPath, view, options);
        }
    }


    @Override
    public RequestOptions getDefaultOptions(@Nullable RequestOptions options) {

        RequestOptions tagOptions;
        if (options == null) {
            tagOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .dontAnimate()
                    .dontTransform();
        } else {
            tagOptions = options;
        }
        return tagOptions;
    }


    @Override
    public void cancelGet(Context context, ImageView imageView) {
        Glide.with(context).clear(imageView);
    }

}
