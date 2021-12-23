package com.courseproject.kate.vkvisualizer.service.impl;

import com.courseproject.kate.vkvisualizer.config.VkVisualizerConfig;
import com.courseproject.kate.vkvisualizer.dto.UserInfoResponse;
import com.courseproject.kate.vkvisualizer.model.UserInfo;
import com.courseproject.kate.vkvisualizer.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private static final String USERS_GET = "/users.get?user_ids={user_ids}&fields={fields}&access_token={token}&v={v}";

    private final RestTemplate template;
    private final VkVisualizerConfig config;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.M.yyyy");

    @Override
    public List<UserInfo> getUserInfo(List<String> userIds, List<String> fields) {
        final Map<String, Serializable> params = new HashMap<>() {{
            put("user_ids", String.join(",", userIds));
            put("fields", String.join(",", fields));
            put("token", config.getToken());
            put("v", config.getVersion());
        }};
        final UserInfoResponse res = template.getForObject(USERS_GET, UserInfoResponse.class, params);
        if (res == null) {
            return null;
        }
        return res.getBody().stream()
                .filter(i -> i.getBDate() != null && i.getBDate().split("\\.").length == 3)
                .map(this::userMapper)
                .collect(Collectors.toList());
    }

    private UserInfo userMapper(UserInfoResponse.UserInfo userInfo) {
        return new UserInfo()
                .setId(userInfo.getId())
                .setFirstName(userInfo.getFirstName())
                .setLastName(userInfo.getLastName())
                .setCanAccessClosed(userInfo.getCanAccessClosed())
                .setIsClosed(userInfo.getIsClosed())
                .setBDate(LocalDate.from(formatter.parse(userInfo.getBDate())));
    }
}
