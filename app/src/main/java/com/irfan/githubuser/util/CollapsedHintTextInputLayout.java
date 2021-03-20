package com.irfan.githubuser.util;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Method;

public class CollapsedHintTextInputLayout extends TextInputLayout {
    Method collapseHintMethod;

    public CollapsedHintTextInputLayout(Context context) {
        super(context);
        init();
    }

    public CollapsedHintTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CollapsedHintTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        setHintAnimationEnabled(false);

        try {
            collapseHintMethod = TextInputLayout.class.getDeclaredMethod("collapseHint", boolean.class);
            collapseHintMethod.setAccessible(true);
        }
        catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        try {
            collapseHintMethod.invoke(this, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
