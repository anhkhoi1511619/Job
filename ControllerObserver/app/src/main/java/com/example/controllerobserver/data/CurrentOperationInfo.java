package com.example.controllerobserver.data;

public class CurrentOperationInfo {
    public static boolean isFtpPermission = false;
    public static String statusInfo = "00";

    public static boolean isIsFtpPermission() {
        return isFtpPermission;
    }

    public static void setIsFtpPermission(boolean isFtpPermission) {
        CurrentOperationInfo.isFtpPermission = isFtpPermission;
    }

    public static String getStatusInfo() {
        return statusInfo;
    }

    public static void setStatusInfo(String statusInfo) {
        CurrentOperationInfo.statusInfo = statusInfo;
    }
}
