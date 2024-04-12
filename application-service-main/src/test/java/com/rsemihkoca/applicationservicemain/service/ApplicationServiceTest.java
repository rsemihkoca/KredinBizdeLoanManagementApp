package com.rsemihkoca.applicationservicemain.service;

import com.rsemihkoca.applicationservicemain.dto.request.CreateApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;
import com.rsemihkoca.applicationservicemain.dto.response.MergedLoanResponse;
import com.rsemihkoca.applicationservicemain.model.Application;
import com.rsemihkoca.applicationservicemain.model.ApplicationPipeline;
import com.rsemihkoca.applicationservicemain.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicationPipeline applicationPipeline;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ApplicationService applicationService;


    @Test
    void test_createApplication_should_returnApplicationResponse_when_success() {
        // Given
        CreateApplicationRequest request = CreateApplicationRequest.builder()
                .bankName("testBankName")
                .email("test@gmail.com")
                .loanId(1L)
                .build();

        MergedLoanResponse mergedLoanResponse = MergedLoanResponse.builder()
                .loanId(1L)
                .amount(1000.0)
                .duration(12)
                .interestRate(1.0)
                .bankName("testBankName")
                .type("testType")
                .build();

        ApplicationResponse expectedResponse = ApplicationResponse.builder()
                .userEmail("test@gmail.com")
                .applicationStatus("CREATED")
                .applicationDate("2021-08-01T00:00:00")
                .mergedLoanResponse(null)
                .build();

        when(applicationPipeline.getCurrentUserEmail()).thenReturn("test@gmail.com");
        when(applicationPipeline.getCurrentLoanRequest()).thenReturn(mergedLoanResponse);
        when(applicationRepository.save(Mockito.any(Application.class))).thenReturn(new Application());
        when(modelMapper.map(Mockito.any(Application.class), Mockito.eq(ApplicationResponse.class)))
                .thenReturn(expectedResponse);

        // When
        ApplicationResponse response = applicationService.createApplication(request);

        // Then
        assertAll(
                () -> assertEquals(mergedLoanResponse, response.getMergedLoanResponse()),
                () -> assertEquals(expectedResponse.getUserEmail(), response.getUserEmail()),
                () -> assertEquals(expectedResponse.getApplicationStatus(), response.getApplicationStatus()),
                () -> assertEquals(expectedResponse.getApplicationDate(), response.getApplicationDate()),
                () -> verify(applicationRepository, times(1)).save(Mockito.any(Application.class)),
                () -> verify(modelMapper, times(1)).map(Mockito.any(Application.class), Mockito.eq(ApplicationResponse.class)),
                () -> verify(applicationPipeline, times(1)).getCurrentUserEmail(),
                () -> verify(applicationPipeline, times(1)).getCurrentLoanRequest(),
                () -> verifyNoMoreInteractions(applicationRepository, modelMapper, applicationPipeline)
        );
    }

    @Test
    void test_getByEmail_should_returnListOfApplicationResponse_when_success() {
        // Given
        String email = "test@gmail.com";
        Application application = new Application();
        when(applicationRepository.findActiveApplicationsByUserEmail(email))
                .thenReturn(Collections.singletonList(application));
        ApplicationResponse expectedResponse = new ApplicationResponse();
        when(modelMapper.map(Mockito.any(Application.class), Mockito.eq(ApplicationResponse.class)))
                .thenReturn(expectedResponse);

        // When
        List<ApplicationResponse> responses = applicationService.getByEmail(email);

        // Then
        assertEquals(1, responses.size());
        assertEquals(expectedResponse, responses.get(0));
    }

    @Test
    void test_getByEmail_should_returnEmptyList_when_noApplicationFound() {
        // Given
        String email = "test@gmail.com";
        when(applicationRepository.findActiveApplicationsByUserEmail(email))
                .thenReturn(Collections.emptyList());

        // When
        List<ApplicationResponse> responses = applicationService.getByEmail(email);

        // Then
        assertEquals(0, responses.size());
    }

    @Test
    void test_getAll_should_returnListOfApplicationResponse_when_success() {
        // Given
        Application application = new Application();
        when(applicationRepository.findAllActiveApplications())
                .thenReturn(Collections.singletonList(application));
        ApplicationResponse expectedResponse = new ApplicationResponse();
        when(modelMapper.map(Mockito.any(Application.class), Mockito.eq(ApplicationResponse.class)))
                .thenReturn(expectedResponse);

        // When
        List<ApplicationResponse> responses = applicationService.getAll();

        // Then
        assertEquals(1, responses.size());
        assertEquals(expectedResponse, responses.get(0));
    }

    @Test
    void test_getAll_should_returnEmptyList_when_noApplicationFound() {
        // Given
        when(applicationRepository.findAllActiveApplications())
                .thenReturn(Collections.emptyList());

        // When
        List<ApplicationResponse> responses = applicationService.getAll();

        // Then
        assertEquals(0, responses.size());
    }

    @Test
    void test_getApplicationByUserEmailAndLoanId_should_returnApplication_when_success() {
        // Given
        String userEmail = "test@gmail.com";
        Long loanId = 1L;
        Application application = new Application();
        when(applicationRepository.findActiveApplicationByUserEmailAndLoanId(userEmail, loanId))
                .thenReturn(application);

        // When
        Application result = applicationService.getApplicationByUserEmailAndLoanId(userEmail, loanId);

        // Then
        assertEquals(application, result);
    }
}

