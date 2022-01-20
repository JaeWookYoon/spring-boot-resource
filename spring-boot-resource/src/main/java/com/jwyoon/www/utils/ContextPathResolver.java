package com.jwyoon.www.utils;

import javax.servlet.http.HttpServletRequest;

public class ContextPathResolver {

    public String contextPathResolve(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();

        return url.toString().replace(request.getRequestURI(), "");
    }
}