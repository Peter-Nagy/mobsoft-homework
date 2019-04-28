package io.swagger.client.auth;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyAuth implements Interceptor {
    private final String paramName;

    private String apiKey;

    public ApiKeyAuth(String apiKey) {
        this.paramName = "api_key";
        this.apiKey = apiKey;
    }

    public String getParamName() {
        return paramName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String paramValue;
        Request request = chain.request();

        String newQuery = request.url().uri().getQuery();
        paramValue = paramName + "=" + apiKey;
        if (newQuery == null) {
            newQuery = paramValue;
        } else {
            newQuery += "&" + paramValue;
        }

        URI newUri;
        try {
            newUri = new URI(request.url().uri().getScheme(), request.url().uri().getAuthority(),
                request.url().uri().getPath(), newQuery, request.url().uri().getFragment());
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }

        request = request.newBuilder().url(newUri.toURL()).build();
        return chain.proceed(request);
    }
}
