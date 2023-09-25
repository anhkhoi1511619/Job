package com.example.controllerobserver.work;

import android.content.Context;

public class DownloadSubAppList extends Job{
    public DownloadSubAppList(Context context) {
        super(context);
    }

    @Override
    void doRun() {
        setStatus(Status.SKIPPED);
    }
}
