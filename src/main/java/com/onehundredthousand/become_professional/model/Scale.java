package com.onehundredthousand.become_professional.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
@Data
public class Scale {
    private String id;
    private String purpose;
    private ArrayList<HashMap<String, Integer>> timestamps;

}
