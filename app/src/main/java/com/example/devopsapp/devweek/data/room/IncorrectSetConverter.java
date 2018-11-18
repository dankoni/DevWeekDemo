package com.example.devopsapp.devweek.data.room;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

public class IncorrectSetConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Set<String> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptySet();
        }

        Type listType = new TypeToken<Set<String>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(Set<String> someObjects) {
        return gson.toJson(someObjects);
    }
}
