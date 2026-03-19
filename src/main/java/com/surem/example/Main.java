package com.surem.example;

import com.surem.core.SureMClient;

public class Main {
    public static void main(String[] args) throws Exception {
        SureMClient client = new SureMClient("USER_CODE", "SECRET_KEY");

        SMSExample.sendSMS(client);
        MMSExample.sendLMS(client);
        MMSExample.sendMMS(client);
        AlimtalkExample.sendAlimtalkText(client);
        AlimtalkExample.sendAlimtalkTextWithButtons(client);
        INTLExample.sendINTL(client);
        AlimtalkExample.sendBrandMessageText(client);
    }
}