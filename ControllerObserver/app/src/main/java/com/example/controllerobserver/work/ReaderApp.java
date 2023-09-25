package com.example.controllerobserver.work;

import android.content.Context;

public class ReaderApp extends Job{
    public ReaderApp(Context context) {
        super(context);
    }

    @Override
    void doRun() {
        setStatus(Status.DONE);
    }
}
