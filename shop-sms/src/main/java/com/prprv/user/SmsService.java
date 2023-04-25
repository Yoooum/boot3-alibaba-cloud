package com.prprv.user;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@Component
@Slf4j
@RequiredArgsConstructor
public class SmsService {

    @Value("${sms.secretId}")
    private String secretId;

    @Value("${sms.secretKey}")
    private String secretKey;

    @GetMapping(value = "/sms", params = {"phone", "code"})
    public void sendSms(@RequestParam String phone, @RequestParam String code) {
        if (!StringUtils.hasText(secretId) && StringUtils.hasText(secretKey)) {
            log.error("secretId or secretKey is null");
            return;
        }

        log.info("phone: {}, code: {}", phone, code);

        try {
            SmsClient client = new SmsClient(new Credential(secretId, secretKey), "ap-guangzhou");
            SendSmsRequest req = new SendSmsRequest();
            req.setPhoneNumberSet(new String[]{phone});
            req.setTemplateId("1764281");
            req.setSmsSdkAppId("1400800226");
            req.setSignName("PRPRV公众号");
            req.setTemplateParamSet(new String[]{code});
            System.out.println(SendSmsResponse.toJsonString(client.SendSms(req)));
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
    }

    public void sms(String phone, String code, String templateId, String appId, String signName) {
        sendSms(phone, code);
    }
}
