package com.bubble.swcamp.android.adapter;

import android.content.Context;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bubble.swcamp.android.R;
import com.bumptech.glide.Glide;
import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainRankerPagerAdapter extends PagerAdapter {
    private final int MAX_POOL_SIZE = 5;
    private Context mContext;
    private Pools.SimplePool<View> viewPool;

    public MainRankerPagerAdapter(Context context) {
        this.mContext = context;
        viewPool = new Pools.SynchronizedPool<>(MAX_POOL_SIZE);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getPagerItemView();
        ImageView background = (ImageView)view.findViewById(R.id.background);
        Glide.with(mContext).load(R.drawable.main1+position).into(background);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        viewPool.release((View) object);
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private View getPagerItemView() {
        View view = viewPool.acquire();
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.main_ranker, null);
        }

        return view;
    }
}
