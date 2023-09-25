package com.example.controllerobserver.work;

import android.content.Context;

public class DownloadAppList extends Job{
    public DownloadAppList(Context context) {
        super(context);
    }

    @Override
    void doRun() {
        setStatus(Status.DONE);
    }
}
