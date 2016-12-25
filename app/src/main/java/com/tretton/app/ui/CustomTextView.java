package com.tretton.app.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tretton.app.R;


public class CustomTextView extends TextView
{


    public CustomTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setAntiAlliasSubPixel();
        parseAttributes(context, attrs);

    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setAntiAlliasSubPixel();
        parseAttributes(context, attrs);
    }


    public CustomTextView(Context context)
    {
        super(context);
        setAntiAlliasSubPixel();
    }


    public void setAntiAlliasSubPixel()
    {
        this.getPaint().setAntiAlias(true);
        this.getPaint().setSubpixelText(true);
    }

    private void parseAttributes(Context context, AttributeSet attrs)
    {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CustomText);

        //The value 0 is a default, but shouldn't ever be used since the attr is an enum
        int typeface = values.getInt(R.styleable.CustomText_typeface, 0);

        switch (typeface)
        {
            case 0:
            default:
                setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/Roboto-Regular.ttf"));
                break;
            case 1:
                setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/Roboto-Medium.ttf"));
                break;
            case 2:
                setTypeface(Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/Roboto-Light.ttf"));
                break;
        }

        values.recycle();
    }

    public boolean isEmpty()
    {
        return this.getText().toString().length() <= 0;
    }
}
