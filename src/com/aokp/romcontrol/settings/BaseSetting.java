package com.aokp.romcontrol.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aokp.romcontrol.R;

/**
 * Created by roman on 12/15/13.
 */
public abstract class BaseSetting extends FrameLayout {

    protected String aKey, aTable, aTitle, aSummary, aDefaultValue;

    protected TextView mTitleTextView, mDescriptionTextView;
    protected LinearLayout mRootView;

    public BaseSetting(Context context) {
        this(context, null);
    }

    public BaseSetting(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseSetting(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseSetting);

            aKey = typedArray.getString(R.styleable.BaseSetting_key);
//            if (aKey == null || aKey.isEmpty()) {
//                throw new UnsupportedOperationException();
//            }

            // set defaults
            String tempTable = typedArray.getString(R.styleable.BaseSetting_table);
            if (tempTable == null) {
                aTable = "aokp";
            } else {
                aTable = tempTable;
            }

            aTitle = typedArray.getString(R.styleable.BaseSetting_title);
            aSummary = typedArray.getString(R.styleable.BaseSetting_summary);
            aDefaultValue = typedArray.getString(R.styleable.BaseSetting_defaultValue);

        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }

        mRootView = (LinearLayout) View.inflate(context, R.layout.setting_base, null);
        mTitleTextView = (TextView) mRootView.findViewById(R.id.title);
        mDescriptionTextView = (TextView) mRootView.findViewById(R.id.summary);
    }

    protected final void setValue(String s) {
        if ("system".equalsIgnoreCase(getTable())) {
            // return Settings.System.putString(getContext().getContentResolver(), s);
        } else {
            //  return Settings.AOKP.put(getContext().getContentResolver(), s);
        }
    }

    protected String getValue() {
        if ("system".equalsIgnoreCase(getTable())) {
            return Settings.System.getString(getContext().getContentResolver(), getKey());
        } else {
//              return Settings.AOKP.getString(getContext().getContentResolver(), getKey());
        }
        return null;
    }

    protected final String getTable() {
        return aTable;
    }

    protected final String getKey() {
        return aKey;
    }

}
