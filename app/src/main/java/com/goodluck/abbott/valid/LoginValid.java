package com.goodluck.abbott.valid;

import android.app.Activity;
import android.content.Context;

import com.goodluck.abbott.LoginActivity;
import com.goodluck.abbott.UserConfigCache;
import com.toptechs.libaction.action.Valid;

/**
 * Created by jinyabo on 8/12/2017.
 */

public class LoginValid implements Valid {
    private Context context;

    public LoginValid(Context context) {
        this.context = context;
    }

    /**
     * check whether it login in or not
     * @return
     */
    @Override
    public boolean check() {
        return UserConfigCache.isLogin(context);
    }


    /**
     * if check() return false. then doValid was called
     */
    @Override
    public void doValid() {
         LoginActivity.start((Activity) context);
    }
}
