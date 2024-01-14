package com.example.domain.helpers;

import lombok.Data;

import java.util.Arrays;
import java.util.Iterator;

@Data
public class NameQuantityPair {
    private final String name;
    private final int quantity;

    public static String getJsonString(NameQuantityPair[] arr) {
        StringBuilder jsonString = new StringBuilder("[");
        Iterator<NameQuantityPair> it = Arrays.stream(arr).iterator();

        while (it.hasNext()) {
            NameQuantityPair pair = it.next();
            String jsonObject = String.format("{%s: %s}", pair.getName(), pair.getQuantity());
            jsonString.append(jsonObject);
            if (it.hasNext()) {
                jsonString.append(",");
            }
        }
        jsonString.append("]");
        return jsonString.toString();
    }
}
