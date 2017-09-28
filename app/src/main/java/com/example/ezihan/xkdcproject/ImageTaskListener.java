package com.example.ezihan.xkdcproject;

import com.example.ezihan.xkdcproject.Model.XkcdModel;

/**
 * Created by EziHAn on 27.09.2017.
 */

public interface ImageTaskListener {
    void preTask();
    void postTask(XkcdModel result);
}
