package com.wrq.fingerprintlocation.dao;

import com.wrq.fingerprintlocation.bean.Fingerprint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 呆呆
 * @Datetime 2021/11/29 17:56
 */
public interface FingerprintDao extends JpaRepository<Fingerprint, Integer> {
}
