package com.wrq.fingerprintlocation.controller;

import com.wrq.fingerprintlocation.bean.Pair;
import com.wrq.fingerprintlocation.service.FingerprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 呆呆
 * @Datetime 2021/11/29 17:55
 */
@Controller
public class FingerprintController {

    @Autowired
    private FingerprintService fingerprintService;

    @RequestMapping("index")
    public String findPoint() {
        return "index";
    }

    @RequestMapping("find")
    public String findPoint(ModelMap modelMap, String text) {
        //15.08,21.3,144
        String[] split = text.trim().split(",");
        List<Double> signals = new ArrayList<>();
        for (String s : split) {
            signals.add(Double.valueOf(s));
        }
        Pair point = fingerprintService.findPoint(signals);
        modelMap.put("point", point);
        return "signal";
    }

}
