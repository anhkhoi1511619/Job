package com.example.controllerobserver.work;

import android.content.Context;

public class DownloadSubApp extends Job{
    public DownloadSubApp(Context context) {
        super(context);
    }

    @Override
    void doRun() {
        setStatus(Status.DONE);
    }
}
