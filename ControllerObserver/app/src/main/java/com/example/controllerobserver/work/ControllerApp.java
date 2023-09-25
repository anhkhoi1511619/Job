package com.example.controllerobserver.work;

import android.content.Context;

public class ControllerApp extends Job{
    public ControllerApp(Context context) {
        super(context);
    }

    @Override
    void doRun() {
        setStatus(Status.DONE);
    }
}
