package com.beertag.http.base;

import java.io.IOException;
import java.util.List;

public interface HttpRequester {
    String get(String url) throws IOException;

    String post(String url, String body) throws IOException;

    void delete(String url, int id) throws IOException;

    void delete(String url, int id,List<Integer> ids) throws IOException;

    String update(String url,String body , int id) throws IOException;

    String update (String url,int firstId,int secondId)throws IOException;

    String update (String url,String body,int firstId,int secondId)throws IOException;
}
