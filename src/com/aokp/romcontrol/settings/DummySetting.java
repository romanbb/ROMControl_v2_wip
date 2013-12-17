package com.aokp.romcontrol.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by roman on 12/17/13.
 */
public class DummySetting extends BaseSetting implements OnClickListener {
    public DummySetting(Context context) {
        this(context, null);
    }

    public DummySetting(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DummySetting(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        addView(mRootView);
        setFocusable(true);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
