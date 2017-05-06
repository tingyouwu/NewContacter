package com.future.wk.newcontacter.base;

import android.content.Intent;

public abstract class OnActivityResultListener {
    public OnActivityResultListener() {}

    public abstract void onResult(Intent data);

}