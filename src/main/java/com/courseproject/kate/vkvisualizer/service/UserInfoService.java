package com.courseproject.kate.vkvisualizer.service;

import com.courseproject.kate.vkvisualizer.model.UserInfo;
import com.courseproject.kate.vkvisualizer.model.UsersByLastSeen;
import com.courseproject.kate.vkvisualizer.model.UsersBySexBdate;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getUserInfo(List<String> userIds, List<String> fields, Integer lang);
    List<UsersBySexBdate.Response.Item> getUsersBySexBdate(Integer count, List<String> fields, Integer city);
    List<UsersByLastSeen.Response.Item> getUsersByLastSeen(Integer count, List<String> fields, Integer city);
}
