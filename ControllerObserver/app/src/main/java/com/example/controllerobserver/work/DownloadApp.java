package com.example.controllerobserver.work;

import android.content.Context;

public class DownloadApp extends Job{
    public DownloadApp(Context context) {
        super(context);
    }

    @Override
    void doRun() {
        setStatus(Status.SKIPPED);
    }
}
