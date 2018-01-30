package com.ryl.mypiclibdemo.glid;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
    public class YourAppGlideModule extends AppGlideModule {
        @Override
        public void applyOptions(Context context, GlideBuilder builder) {
            super.applyOptions(context,builder);
            builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));
        }
    }