package de.iekadou.pjaxr;

import javax.servlet.http.HttpServletRequest;

public class Pjaxr {

    private String currentNamespace;
    private String previousNamespace = "";
    private String matchingNamespace = "";
    private int matchingCount = 0;

    public boolean pjaxr_site = true;
    public boolean pjaxr_page = true;
    public boolean pjaxr_content = true;
    public boolean pjaxr_inner_content = true;
    
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

        this.pjaxr_site = this.matchingCount <= 0;
        this.pjaxr_page = this.matchingCount <= 1;
        this.pjaxr_content = this.matchingCount <= 2;
        this.pjaxr_inner_content = this.matchingCount <= 3;
    }

    public String getMatchingNamespace() {
        return matchingNamespace;
    }

    public int getMatchingCount() {
        return matchingCount;
    }
}