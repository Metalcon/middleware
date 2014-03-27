package com.iekadou.pjaxr;

import javax.servlet.http.HttpServletRequest;

public class Pjaxr {

    protected String currentNamespace;
    protected String previousNamespace = "";
    protected String matchingNamespace = "";
    protected int matchingCount = 0;

    public Pjaxr(HttpServletRequest request, String currentNamespace) {
        this.currentNamespace = currentNamespace;

        if (request.getHeader("X-PJAX") != null && request.getHeader("X-PJAX-NAMESPACE") != null) {
            this.previousNamespace = request.getHeader("X-PJAX-NAMESPACE"); 
            String[] prevNamespaces = this.previousNamespace.split("\\.");
            String[] currentNamespaces = this.currentNamespace.split("\\.");
            String matchedNamespace = "";
            for (int level = 0; level < currentNamespaces.length; level++) {
                if (prevNamespaces.length > level) {
                    if (currentNamespaces[level].equals(prevNamespaces[level])) {
                        matchedNamespace += (level > 0) ? "."+ currentNamespaces[level] : currentNamespaces[level];
                        // +1 for better recoginzable Values and the ability to decide
                        // if pjaxr should be used or not with testing against 0
                        this.matchingCount = level + 1;
                    } else {
                        break;
                    }
                }
            }
            this.matchingNamespace = matchedNamespace;
        }
    }

    public String getMatchingNamespace() {
        return matchingNamespace;
    }

    public int getMatchingCount() {
        return matchingCount;
    }
}
