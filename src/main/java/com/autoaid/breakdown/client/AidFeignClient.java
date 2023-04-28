package com.autoaid.breakdown.client;

import com.autoaid.breakdown.model.AidProvider;
import com.autoaid.breakdown.model.AidRequest;
import com.autoaid.breakdown.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "AidService")
public interface AidFeignClient {
    @GetMapping("/aidapi/provider/all")
    List<AidProvider> getAllAidProviderAvailable();

    @PostMapping("/aidapi/aid/request")
    AidRequest requestAid(@RequestBody AidRequest aidRequest);
}
