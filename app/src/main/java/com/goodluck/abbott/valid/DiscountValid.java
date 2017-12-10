package com.goodluck.abbott.valid;

import android.app.Activity;
import android.content.Context;

import com.goodluck.abbott.DiscountActivity;
import com.goodluck.abbott.UserConfigCache;
import com.toptechs.libaction.action.Valid;

/**
 * Created by jinyabo on 8/12/2017.
 */

public class DiscountValid implements Valid {
    private Context context;

    public DiscountValid(Context context) {
        this.context = context;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean check() {
        return UserConfigCache.isDiscount(context);
    }


    /**
     * if check() return false. then doValid was called
     */
    @Override
    public void doValid() {
         DiscountActivity.start((Activity) context);
    }
}
