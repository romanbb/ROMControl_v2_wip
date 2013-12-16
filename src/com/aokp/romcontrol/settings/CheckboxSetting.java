package com.aokp.romcontrol.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import com.aokp.romcontrol.R;

/**
 * Created by roman on 12/15/13.
 */
public class CheckboxSetting extends BaseSetting implements OnClickListener {

    CheckBox mCheckBox;

    String aDescriptionOn, aDescriptionOff;

    Boolean mChecked = false;


    public CheckboxSetting(Context context) {
        this(context, null);
    }

    public CheckboxSetting(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckboxSetting(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckboxSetting);

            mChecked = typedArray.getBoolean(R.styleable.CheckboxSetting_checkDefaultValue, false);
            aDescriptionOn = typedArray.getString(R.styleable.CheckboxSetting_descriptionOn);
            aDescriptionOff = typedArray.getString(R.styleable.CheckboxSetting_descriptionOff);
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }

        /**
         * Inflate Views
         */
        addView(View.inflate(context, R.layout.setting_checkbox, mRootView));
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);


        /**
         * Setup initial logic
         */
        mTitleTextView.setText(aTitle);
        updateSummary();
        mCheckBox.setChecked(isChecked());

        setOnClickListener(this);
        setFocusable(true);
    }

    private void updateSummary() {
        if (mDescriptionTextView != null) {
            if (aSummary == null) {
                if (aDescriptionOff != null && aDescriptionOn != null) {
                    mDescriptionTextView.setText(isChecked() ? aDescriptionOn : aDescriptionOff);
                } else {
                    mDescriptionTextView.setVisibility(View.GONE);
                }
            } else {
                mDescriptionTextView.setText(aSummary);
            }
        }
    }

    @Override
    public void onClick(View v) {
        setChecked(!isChecked());
        updateSummary();
    }

    private void setChecked(boolean checked) {
        if (checked != mChecked) {
            setValue(checked ? "1" : "0");
            mChecked = checked;
        }
        mCheckBox.setChecked(checked);
    }

    public boolean isChecked() {
        return mChecked;
    }
}
