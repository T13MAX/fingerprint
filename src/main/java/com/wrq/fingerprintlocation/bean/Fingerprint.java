package com.wrq.fingerprintlocation.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 呆呆
 * @Datetime 2021/11/29 17:56
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fingerprint")
public class Fingerprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double xLabel;

    private double yLabel;

    private int localLable;

    private int roomLabel;

    private String signals;

    @Transient
    private List<Double> signalList;

    @Transient
    private double Ei;

    public void parse() {
        String[] split = this.signals.split(",");
        signalList = new ArrayList<>();
        for (String s : split) {
            signalList.add(Double.valueOf(s));
        }
    }

}
