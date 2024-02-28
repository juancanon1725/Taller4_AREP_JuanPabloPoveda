package org.example;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private static Cache instance;

    private static ConcurrentHashMap<String, String> cache;


    public Cache(){
        cache = new ConcurrentHashMap<String, String>();
    }

    public boolean isOnCache(String title){
        return cache.containsKey(title);
    }


    public void addMovie(String title, String json){
        cache.put(title, json);
    }

    public String getMovieDescription(String title){
        return cache.get(title);
    }

    public static Cache getInstance() {

        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }


    public int size(){
        return cache.size();
    }

    public void clear(){cache.clear();}
}