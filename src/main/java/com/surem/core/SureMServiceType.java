package com.surem.core;


public enum SureMServiceType {

    AUTH("/api/v1/auth/token"),
    SMS("/api/v1/send/sms"),
    MMS("/api/v1/send/mms"),
    ALIMTALK("/api/v1/send/alimtalk"),
    BRAND_MESSAGE("/api/v1/send/brand"),
    RCS("/api/v1/send/rcs"),
    INTL("/api/v1/send/intl");

    final String servicePath;

    SureMServiceType(String servicePath) {
        this.servicePath = servicePath;
    }
}
