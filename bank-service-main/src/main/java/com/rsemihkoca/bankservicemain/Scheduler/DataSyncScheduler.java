package com.rsemihkoca.bankservicemain.Scheduler;

import com.rsemihkoca.bankservicemain.client.ClientFactory;
import com.rsemihkoca.bankservicemain.dto.response.GenericResponse;
import com.rsemihkoca.bankservicemain.dto.response.LoanResponse;
import com.rsemihkoca.bankservicemain.dto.response.MergedLoanResponse;
import com.rsemihkoca.bankservicemain.enums.BankType;
import com.rsemihkoca.bankservicemain.entity.Constants;
import com.rsemihkoca.bankservicemain.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
@Slf4j
public class DataSyncScheduler {

    private ClientFactory clientFactory;

    private RedisService redisService;

    private ModelMapper modelMapper;

    @Scheduled(fixedDelay = 60000) // 1 dakika
    public void syncDataToRedis() {

        List<MergedLoanResponse> data = new ArrayList<>();
        for(BankType bankType : BankType.values()) {
            GenericResponse<List<LoanResponse>> bank = clientFactory.createBankClient(bankType).getAll().getBody();
            if (bank == null) {
//                throw new RuntimeException( bankType + " veri alınamadı.");
                log.error(bankType + " veri alınamadı.");
                continue;
            }

            List<MergedLoanResponse> mergedLoanResponses = bank.getData().stream()
                    .map(loanResponse -> modelMapper.map(loanResponse, MergedLoanResponse.class))
                    .peek(mergedLoanResponse -> mergedLoanResponse.setBankName(bankType.toString()))
                    .toList();

            data.addAll(mergedLoanResponses);
        }


//        List<MergedLoanResponse> data = Stream.of(akbank, garanti)
//                .flatMap(List::stream)
//                .toList();

        redisService.save(Constants.LOAN_CACHE, data);
    }
}
