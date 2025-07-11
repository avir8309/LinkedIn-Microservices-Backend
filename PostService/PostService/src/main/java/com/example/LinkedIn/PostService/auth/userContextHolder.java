package com.example.LinkedIn.PostService.auth;

public class userContextHolder {

    private static final ThreadLocal<Long> userContext = new ThreadLocal<>();

    public static Long getContext() {
        return userContext.get();
    }

    public static void setContext(Long context) {
        userContext.set(context);
    }

    public static void clear() {
        userContext.remove();
    }
}
