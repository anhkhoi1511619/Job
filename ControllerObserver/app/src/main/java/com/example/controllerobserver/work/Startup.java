package com.example.controllerobserver.work;

import android.content.Context;
import android.util.Log;

public class Startup extends Job{
    static ContinueRun continueRun;
    ControllerApp controllerApp;
    Download download;
    ReaderApp readerApp;
    public Startup(Context context) {
        super(context);
        continueRun = new ContinueRun(context);
        download = new Download(context);
        controllerApp = new ControllerApp(context);
        readerApp = new ReaderApp(context);
        continueRun.chain(download, ChainCondition.RUN_IF_SUCCESS)
                .then(this::clear)
                .chain(controllerApp, ChainCondition.RUN_IF_SUCCESS)
                .chain(readerApp, ChainCondition.RUN_IF_SUCCESS)
                .then(this::report);
    }

    @Override
    void doRun() {
        continueRun.run();
    }
    void report(){
        Log.v(TAG, "download:  "+download.done());
        Log.v(TAG, "controllerApp:  "+controllerApp.done());
        Log.v(TAG, "readerApp:  "+controllerApp.done());
        boolean success = download.done()&&
                controllerApp.done()&&
                readerApp.done();
        setStatus(success?Status.DONE:Status.FAILED);
    }
}
