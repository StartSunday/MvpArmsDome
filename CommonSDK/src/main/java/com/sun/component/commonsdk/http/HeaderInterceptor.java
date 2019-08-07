/**
 * Copyright (C) 2016 LingDaNet.Co.Ltd. All Rights Reserved.
 */
package com.sun.component.commonsdk.http;

import com.sun.component.commonsdk.constant.Constant;
import com.sun.component.commonsdk.utils.SPUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HeaderInterceptor implements Interceptor {
    private Map<String, String> headers;
    private static final String COOKIE = "Cookie";
    private static final String DATE = "Date";
    private static final String AUTHORIZATION = "Authorization";

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        synchronized (this) {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            String token = SPUtils.getInstance().getString(Constant.TOKEN);
            if (headers != null) {
                headers.put("user-agent", "Android");
                headers.put("token", token);
                if (headers.size() > 0) {
                    Set<String> keys = headers.keySet();
                    for (String headerKey : keys) {
                        builder.addHeader(headerKey, headers.get(headerKey)).build();
                    }
                }
                response = chain.proceed(builder.build());
            } else {
                response = chain.proceed(builder.build());
            }
        }
        return response;
    }
}
