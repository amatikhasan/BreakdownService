package com.autoaid.breakdown.service;

import com.autoaid.breakdown.client.AidFeignClient;
import com.autoaid.breakdown.client.UserFeignClient;
import com.autoaid.breakdown.model.AidProvider;
import com.autoaid.breakdown.model.AidRequest;
import com.autoaid.breakdown.model.Breakdown;
import com.autoaid.breakdown.model.UserInfo;
import com.autoaid.breakdown.repository.BreakdownRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BreakdownService {

    private BreakdownRepository breakdownRepository;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private AidFeignClient aidFeignClient;

    public BreakdownService(BreakdownRepository BreakdownRepository, UserFeignClient userFeignClient, AidFeignClient aidFeignClient) {
        this.breakdownRepository = BreakdownRepository;
        this.userFeignClient = userFeignClient;
        this.aidFeignClient = aidFeignClient;
    }

    public Breakdown createBreakdown(Breakdown Breakdown) {
        return breakdownRepository.save(Breakdown);
    }

    public void createRandomBreakdown() throws IOException{
        File jsonFile = new File("src/main/resources/static/vehicleData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Breakdown[] testData = objectMapper.readValue(jsonFile, Breakdown[].class);
        Breakdown breakdown = testData[new Random().nextInt(testData.length)];
        breakdown.setStatus("Not Fixed");
        System.out.println(breakdown.getModel());
        breakdownRepository.save(breakdown);
    }

    public Optional<Breakdown> getBreakdown(Long id) {
        return breakdownRepository.findById(id);
    }

    public List<Breakdown> getAllBreakdown(){
        return breakdownRepository.findAll();
    }

    public List<Breakdown> getAllTestBreakdown() throws IOException {
        //return breakdownRepository.findAll();

        File jsonFile = new File("src/main/resources/static/vehicleData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Breakdown[] testData = objectMapper.readValue(jsonFile, Breakdown[].class);

        return List.of(testData);
    }

    public Breakdown updateBreakdown(Breakdown Breakdown) {
        return breakdownRepository.save(Breakdown);
    }

    public void deleteBreakdown(Long id) {
        breakdownRepository.deleteById(id);
    }

    public void deleteAllBreakdown() {
        breakdownRepository.deleteAll();
    }

    public UserInfo getUserInfo(Long id) {
        return userFeignClient.getUserInfo(id);
    }

    public List<AidProvider> getAllAidProvider() {
        return aidFeignClient.getAllAidProviderAvailable();
    }

    public AidRequest requestAid(AidRequest aidRequest) {
        return aidFeignClient.requestAid(aidRequest);
    }

}
