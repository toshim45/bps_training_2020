package com.bps.employee.model;

import java.util.HashMap;
import java.util.Map;

public class Employee {
    Map<Integer,Integer> rewards = new HashMap<Integer, Integer>();

    public int incrementRewards(int employeeId){
        int reward = 0;
        if (rewards.containsKey(employeeId)) {
            reward = rewards.get(employeeId);
        }

        rewards.put(employeeId, ++reward);
        return reward;
    }
}
