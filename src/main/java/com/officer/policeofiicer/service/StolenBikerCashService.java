package com.officer.policeofiicer.service;

import com.officer.policeofiicer.domain.PoliceOfficer;
import com.officer.policeofiicer.domain.StolenBiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StolenBikerCashService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void addStolenBiker(StolenBiker stolenBiker){
        redisTemplate.opsForList().rightPush("stolenBiker",stolenBiker);
    }

    public StolenBiker getFreeStolenBiker(){
        return (StolenBiker) redisTemplate.opsForList().leftPop("stolenBiker");
    }
}
