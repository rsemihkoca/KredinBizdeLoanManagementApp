package com.rsemihkoca.bankservicemain.service;

import com.rsemihkoca.bankservicemain.dto.response.MergedLoanResponse;
import com.rsemihkoca.bankservicemain.entity.Loan;
import com.rsemihkoca.bankservicemain.repository.RedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RedisService {

    private RedisTemplate<String, List<MergedLoanResponse>> redisTemplate;

    public List<MergedLoanResponse> getAll(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void save(String key, List<MergedLoanResponse> mergedLoanResponses) {
        redisTemplate.opsForValue().set(key, mergedLoanResponses);
    }
}
