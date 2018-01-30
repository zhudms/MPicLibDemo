package com.ryl.mypiclibdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

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

    protected boolean isScrolling = false;

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.pic_item, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

//tag + 尺寸指定 + 占位符  解决图片串位问题(使用不修改尺寸属性无效),-->必须确定内存中缓存的是原始图片还是屏幕上显示的图片大小
        if (!isScrolling) {

            if (holder.getmTag() != null && holder.getmTag().equals(picPaths.get(position))) {//解决图片错位问题
                //此处判断必须和下面设置占位图一起使用才有效(只使用占位图而不指定图片大小时,在复用中会出现图片大小错位,持续滑动,最终会使所又能图片都变成占位图的大小)
                return;
            }

            holder.setmTag(picPaths.get(position));

            RequestOptions cropOptions = new RequestOptions();
//            cropOptions= cropOptions.fitCenter();//在 xml 中指定也可生效
            cropOptions= cropOptions.placeholder(R.drawable.ic_launcher_foreground);
            cropOptions=  cropOptions.dontAnimate();
            cropOptions=   cropOptions.dontTransform();
            cropOptions=   cropOptions.error(R.drawable.ic_launcher_background);

            cropOptions=    cropOptions.override(Target.SIZE_ORIGINAL);//(加上尺寸指定,滑动顺畅很多)只使用占位图而不指定图片大小时,在复用中会出现图片大小错位,持续滑动,最终会使所又能图片都变成占位图的大小

            Glide.with(mContext)
                    .load(picPaths.get(position))
                    .apply(cropOptions)

                    .into(holder.getmPic());
        } else {
//            holder.mPic.setImageResource(R.drawable.ic_launcher_background);
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
//
//    @GlideModule
//    public class YourAppGlideModule extends AppGlideModule {
//        @Override
//        public void applyOptions(Context context, GlideBuilder builder) {
//            super.applyOptions(context,builder);
//            builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));
//        }
//    }
}
