package com.example.controllerobserver.work;

import android.content.Context;

public class ContinueRun extends Job{
    public ContinueRun(Context context) {
        super(context);
    }

    @Override
    void doRun() {
        setStatus(Status.DONE);
    }
}
