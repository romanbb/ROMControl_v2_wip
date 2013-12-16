package com.aokp.romcontrol.settings;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
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

    public static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    public static final String NAMESPACE_RC = "http://schemas.android.com/apk/res/com.aokp.romcontrol";

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

        if (attrs != null) {
            Resources r = context.getResources();
            aKey = attrs.getAttributeValue(NAMESPACE_ANDROID, "key");

            aTitle = readAttrStringResource(r, attrs.getAttributeResourceValue(NAMESPACE_ANDROID, "title", 0));
            aSummary = readAttrStringResource(r, attrs.getAttributeResourceValue(NAMESPACE_ANDROID, "summary", 0));

            aDefaultValue = attrs.getAttributeValue(NAMESPACE_ANDROID, "defaultValue");
            aTable = attrs.getAttributeValue(NAMESPACE_RC, "table");

            if (aTable == null) {
                aTable = "aokp";
            }
//            TypedArray typedArray = null;
//            try {
//                typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseSetting);
//                String tempTable = typedArray.getString(R.styleable.BaseSetting_table);
//
//            } finally {
//                if (typedArray != null) {
//                    typedArray.recycle();
//                }
//            }
        }

        mRootView = (LinearLayout) View.inflate(context, R.layout.setting_base, null);
        mTitleTextView = (TextView) mRootView.findViewById(R.id.title);
        mDescriptionTextView = (TextView) mRootView.findViewById(R.id.summary);
    }

    public static String readAttrStringResource(Resources r, int resource) {
        try {
            String string = r.getString(resource);
            return string;
        } catch (NotFoundException e) {
            return null;
        }
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
