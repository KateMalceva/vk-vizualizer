package com.courseproject.kate.vkvisualizer.service;

import com.courseproject.kate.vkvisualizer.dto.CitiesResponse;
import com.courseproject.kate.vkvisualizer.dto.RegionsResponse;

public interface CountryInfoService {
    RegionsResponse getRegions(Integer countryId, Integer count);
    CitiesResponse getCities(Integer countyId, Integer regionId, Integer needAll, Integer count);
}
