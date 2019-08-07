package com.sun.component.commonres.eventbus;

public class UpdateApkEvent {
    String apkUrl;
    String version;

    public UpdateApkEvent(String apkUrl, String version) {
        this.apkUrl = apkUrl;
        this.version = version;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
