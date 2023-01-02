package com.onehundredthousand.become_professional.service;

import com.onehundredthousand.become_professional.model.Scale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StartProgress implements EventMaker{

    @Override
    public Scale makeEvent(Integer timestamp, String description) {
        Scale scale = new Scale();
        ArrayList<HashMap<String, Integer>> startProgress = Arrays.asList([])
        scale.setTimestamps();
        return null;
    }
}
