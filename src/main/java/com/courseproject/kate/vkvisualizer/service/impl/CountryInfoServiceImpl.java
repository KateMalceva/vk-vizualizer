package com.courseproject.kate.vkvisualizer.service.impl;

import com.courseproject.kate.vkvisualizer.config.VkVisualizerConfig;
import com.courseproject.kate.vkvisualizer.dto.CitiesResponse;
import com.courseproject.kate.vkvisualizer.dto.RegionsResponse;
import com.courseproject.kate.vkvisualizer.service.CountryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CountryInfoServiceImpl implements CountryInfoService {

    private static final String REGIONS_GET = "/database.getRegions?country_id={country_id}&count={count}&access_token={token}&v={v}";
    private static final String CITIES_GET = "/database.getCities?country_id={country_id}&region_id={region_id}&need_all={need_all}&count={count}&access_token={token}&v={v}";

    private final RestTemplate template;
    private final VkVisualizerConfig config;


    @Override
    public RegionsResponse getRegions(Integer countryId, Integer count) {
        final Map<String, Object> params = new HashMap<>() {{
            put("country_id", countryId);
            put("count", count);
            put("token", config.getToken());
            put("v", config.getVersion());
        }};
        return template.getForObject(REGIONS_GET, RegionsResponse.class, params);
    }

    @Override
    public CitiesResponse getCities(Integer countyId, Integer regionId, Integer needAll, Integer count) {
        final Map<String, Object> params = new HashMap<>() {{
            put("country_id", countyId);
            put("region_id", regionId);
            put("need_all", needAll);
            put("count", count);
            put("token", config.getToken());
            put("v", config.getVersion());
        }};
        return template.getForObject(CITIES_GET, CitiesResponse.class, params);
    }
}
