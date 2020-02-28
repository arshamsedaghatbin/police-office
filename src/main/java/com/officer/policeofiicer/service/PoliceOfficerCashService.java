package com.officer.policeofiicer.service;

import com.officer.policeofiicer.domain.PoliceOfficer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PoliceOfficerCashService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void addFreePolice(PoliceOfficer policeOfficer){
        redisTemplate.opsForList().rightPush("policeOfficer",policeOfficer);
    }

    public PoliceOfficer getFreePolice(){
        return (PoliceOfficer) redisTemplate.opsForList().leftPop("policeOfficer");
    }
}
