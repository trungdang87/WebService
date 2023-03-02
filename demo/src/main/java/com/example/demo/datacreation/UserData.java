package com.example.demo.datacreation;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserData {
    private List<User> allUsers = new ArrayList<>();
    private Map<Long, User> userIdMap = new HashMap<>();
    private Map<String, List<User>> eventTypeMap = new HashMap<>();

    public List<User> getAllUsers() {
        return allUsers;
    }

    public User getUserById(Long id) {
        return userIdMap.get(id);
    }

    public List<User> getUsersByEventType(String eventType) {
        return eventTypeMap.get(eventType);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (CollectionUtils.isEmpty(allUsers)) {
                allUsers = mapper.readValue(new File("/Users/trungdang/project/demo/src/main/resources/data/data.json"), new TypeReference<List<User>>() {
                });
            }
            if (CollectionUtils.isEmpty(userIdMap) && !CollectionUtils.isEmpty(allUsers)) {
                userIdMap = allUsers.stream().collect(Collectors.toMap(User::getId, Function.identity()));
            }
            if(CollectionUtils.isEmpty(eventTypeMap) && !CollectionUtils.isEmpty(allUsers)) {
                for(User u : allUsers) {
                    for(String event : u.getEvent_type().get("type")) {
                        if(eventTypeMap.get(event) != null) {
                            List<User> users = new ArrayList<>();
                            users.addAll(eventTypeMap.get(event));
                            users.add(u);
                            eventTypeMap.put(event, users);
                        } else {
                            eventTypeMap.put(event, List.of(u));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
