package com.rsemihkoca.bankservicemain.repository;

import com.rsemihkoca.bankservicemain.dto.response.MergedLoanResponse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RedisRepository extends CrudRepository<List<MergedLoanResponse>, String> {
}
