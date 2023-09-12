package com.shop.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/paymentServer")
public class paymentController {
    private final IamportClient IamportClient;

    public paymentController(){
        this.IamportClient = new IamportClient("6128576401217345", "hy0qnhwwfw3n3iAoznA4q4FYsC8a9vGu1ZQUGUWfGeN110EXINLvRGMU55jMm3yqEXao8I5E0lEHUmV6");
    }

    @PostMapping("/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws IamportResponseException, IOException{
        log.info("paymentByImpUid 진입");
        return IamportClient.paymentByImpUid(imp_uid);
    }

}
