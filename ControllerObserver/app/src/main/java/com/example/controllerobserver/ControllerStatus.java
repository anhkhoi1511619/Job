package com.example.controllerobserver;

public enum ControllerStatus {
    INIT("00",false),
    REQUEST("01", true),
    EXECUTE("81", false),
    DONE("82", false),
    FINISH("10",true),
    UPDATE("90", true);
    private final String statusInfo;
    private final boolean isFtpPermission;

    ControllerStatus(String statusInfo, boolean isFtpPermission) {
        this.statusInfo = statusInfo;
        this.isFtpPermission = isFtpPermission;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public boolean isFtpPermission() {
        return isFtpPermission;
    }
}
