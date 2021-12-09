package com.wrq.fingerprintlocation.controller;

import com.wrq.fingerprintlocation.bean.Fingerprint;
import com.wrq.fingerprintlocation.bean.Pair;
import com.wrq.fingerprintlocation.service.FingerprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping("list")
    public String findAll(ModelMap modelMap) {
        List<Fingerprint> list = fingerprintService.findAll();
        modelMap.put("list",list);
        return "list";
    }

    @RequestMapping("delete")
    public String deletePoint(ModelMap modelMap,int id) {
        fingerprintService.delete(new Fingerprint(id));
        List<Fingerprint> list = fingerprintService.findAll();
        modelMap.put("list",list);
        return "redirect:/list";
    }

    @RequestMapping("add")
    public String findPoint(ModelMap modelMap,@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        Optional<Fingerprint> op = fingerprintService.findById(id);
        Fingerprint fingerprint;
        if (op.isPresent()){
             fingerprint = op.get();
        }else{
            fingerprint=new Fingerprint();
        }
        modelMap.put("fingerprint",fingerprint);
        return "edit";
    }

    @RequestMapping("save")
    public String deletePoint(ModelMap modelMap,Fingerprint fingerprint) {
        fingerprintService.add(fingerprint);
        return "redirect:/list";
    }

    /*@RequestMapping("save")
    public String deletePoint(ModelMap modelMap,int id, int xLabel,int yLabel,int localLable,int roomLabel,String signals) {
        fingerprintService.add(new Fingerprint(id,xLabel,yLabel,localLable,roomLabel,signals));
        return "list";
    }*/

}
