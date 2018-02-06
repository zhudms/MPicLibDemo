package com.ryl.mypiclibdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ryl.commonlib.utils.LU;
import com.ryl.mypiclibdemo.glid.GlidImageHelper;

import java.util.List;

/**
 * Created by rongyile on 2018/1/29.
 */

public class ImagePicAdapter extends RecyclerView.Adapter<ImagePicAdapter.MyHolder> {

    private List<String> picPaths;
    private Context mContext;


    public ImagePicAdapter(List<String> picPaths, Context mContext) {
        this.picPaths = picPaths;
        this.mContext = mContext;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.pic_item, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    //布局
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

//tag + 尺寸指定 + 占位符  解决图片串位问题(使用不修改尺寸属性无效),-->必须确定内存中缓存的是原始图片还是屏幕上显示的图片大小


        LU.hd("asd", "position=" + position);
        if (holder.getmTag() != null && holder.getmTag().equals(picPaths.get(position))) {//youhua ,哪里解决错位了?解决图片错位问题
            //此处判断必须和下面设置占位图一起使用才有效(只使用占位图而不指定图片大小时,在复用中会出现图片大小错位,持续滑动,最终会使所又能图片都变成占位图的大小)
            return;
        }

        holder.setmTag(picPaths.get(position));


        if (position == 2) {

            RequestOptions options = new GlidImageHelper().round().sepia().toon()
                    .tagSize(MyApplication.winWidth, -1).getOptions();


//            RequestOptions options=new GlidImageHelper().round().sepia()
//                    .tagSize(MyApplication.winWidth,-1).getOptions();

            GlidImageHelper.loadPic(mContext, picPaths.get(position), holder.getmPic(), options);
        } else {


            RequestOptions cropOptions = new RequestOptions();
//            cropOptions= cropOptions.fitCenter();//在 xml 中指定也可生效
            cropOptions = cropOptions.placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .dontAnimate()
                    .dontTransform()

//                .circleCrop();
                .override(MyApplication.winWidth);//(加上尺寸指定,滑动顺畅很多)只使用占位图而不指定图片大小时,在复用中会出现图片大小错位,持续滑动,最终会使所又能图片都变成占位图的大小
//只指定一个尺寸,会使图片按比例缩放,不会变成方形,不指定尺寸,尺寸一定会出问题,一定要加


            Glide.with(mContext)
                    .asBitmap()//asbitmap 方法不会导致问题
                    .load(picPaths.get(position))
                    .apply(cropOptions)

//                .into(new BitmapImageViewTarget(holder.getmPic(),true));
                    .into(holder.getmPic());//直接使用into
//                .into(new SimpleTarget<Bitmap>() {//使用这个方法,会使占位图无法显示,图片出现错位问题,解决方法暂时不明,不要使用
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//
//                        if (holder.getmTag() == picPaths.get(position)) {
//
//                            holder.getmPic().setImageBitmap(BitmapU.getWinWidthBitmap(mContext, resource));
//                        }
//
//                    }
//                });
        }
    }


    @Override
    public int getItemCount() {
        return picPaths.size();
    }


    @Override
    public void onViewRecycled(MyHolder holder) {
        super.onViewRecycled(holder);
        Glide.with(mContext).clear(holder.itemView);
        holder.setmTag(null);
//        ToastU.ht(mContext, "OnRecycled");
    }

    static class MyHolder extends RecyclerView.ViewHolder {

        private ImageView mPic;

        public String getmTag() {
            return mTag;
        }

        public void setmTag(String mTag) {
            this.mTag = mTag;
        }

        private String mTag;

        public MyHolder(View itemView) {
            super(itemView);
            mPic = itemView.findViewById(R.id.item_image);

        }

        public ImageView getmPic() {
            return mPic;
        }

        public void setmPic(ImageView mPic) {
            this.mPic = mPic;
        }


    }
}