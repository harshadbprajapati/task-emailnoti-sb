package com.springbootbackend.springbootbackend.email_service;


import java.util.Collections;
import java.util.Map;

import org.apache.kafka.common.errors.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailTo;

import java.util.HashMap;

@Service
public class EmailSenderService {

    @Value("${sendinblue.apiKey}")
    private String apiKey;
    @Value("${sendinblue.id}")
    private String id;
    public  void sendInBlue(String to,String name){
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            params.put("to", to);
            params.put("subject","Successful Registration!!");
            params.put("params", new HashMap<String, Object>() {{
                put("NAME", name);
            }});

            ApiClient apiClient = new ApiClient();
            apiClient.setApiKey(apiKey);
            TransactionalEmailsApi emailApi = new TransactionalEmailsApi(apiClient);

            SendSmtpEmail smtpEmail = new SendSmtpEmail();
            smtpEmail.setTo(Collections.singletonList(new SendSmtpEmailTo().email(to)));
            smtpEmail.setTemplateId(Long.valueOf(id));
            smtpEmail.setParams(params);

            emailApi.sendTransacEmail(smtpEmail);
            System.out.println("MailSent");
        } catch (ApiException e) {
            System.out.println(e);
        } catch (sendinblue.ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
