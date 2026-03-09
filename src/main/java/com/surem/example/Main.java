package com.surem.example;

import com.surem.core.SureMClient;

public class Main {
    public static void main(String[] args) throws Exception {
        SureMClient client = new SureMClient("__USERCODE__", "__SECRET_KEY__");

        SMSExample.sendSMS(client);
//        MMSExample.sendLMS(client);
//        MMSExample.sendMMS(client);
    }
}