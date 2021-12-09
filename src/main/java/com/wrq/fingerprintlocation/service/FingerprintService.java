package com.wrq.fingerprintlocation.service;

import com.wrq.fingerprintlocation.bean.Fingerprint;
import com.wrq.fingerprintlocation.bean.Pair;
import com.wrq.fingerprintlocation.dao.FingerprintDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @Author 呆呆
 * @Datetime 2021/11/29 17:55
 */
@Service
public class FingerprintService {

    @Autowired
    private FingerprintDao fingerprintDao;

    public List<Fingerprint> findAll() {
        return fingerprintDao.findAll();
    }

    public void delete(Fingerprint fingerprint){
        fingerprintDao.delete(fingerprint);
    }

    public void add(Fingerprint fingerprint){
        fingerprintDao.save(fingerprint);
    }

    public Optional<Fingerprint> findById(int id){
         return fingerprintDao.findById(id);
    }

    public Pair findPoint(List<Double> signals) {
        List<Fingerprint> fingerprintList = findAll();
        for (Fingerprint fingerprint : fingerprintList) {
            fingerprint.parse();
            double sum = 0;
            for (int i = 0; i < fingerprint.getSignalList().size(); i++) {
                sum += Math.sqrt(Math.pow(signals.get(i) - fingerprint.getSignalList().get(i), 2));
            }
            fingerprint.setEi(sum);
        }
        fingerprintList.sort(Comparator.comparing(e -> e.getEi()));
        /*for (Fingerprint fingerprint : fingerprintList) {
            System.out.println(fingerprint.getXLabel() + "---" + fingerprint.getYLabel() + "---" + fingerprint.getEi());
        }*/
        return new Pair(fingerprintList.get(0).getXLabel(), fingerprintList.get(0).getYLabel());
    }

    /*@Scheduled(initialDelay = 1000, fixedDelay = 1055500 * 1000)
    public void findPoint(){
        //15.08,21.3,144
        String test="-79,-78,-66.7,-60.4,-54.6,-45.6,-53.1,-65,-70.4,-77.8,-68.1,-60.1,-88,-67.3,-80.4,-59.9,-53.9,-45.4,-52.9,-64.4,-70.3,-78.5,-67.9,-60.4,-63.6,-63.1,-46.8,-74.8,-72.2,-59.4";
        String[] split = test.split(",");
        List<Double> signals=new ArrayList<>();
        for (String s : split) {
            signals.add(Double.valueOf(s));
        }
        findPoint(signals);
    }*/

}
