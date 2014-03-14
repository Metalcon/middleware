package de.iekadou.spring_pjaxr;

import javax.servlet.http.HttpServletRequest;

public class Pjaxr {

    private String currentNamespace;
    private String previousNamespace = "";
    private String matchingNamespace = "";
    private int matchingCount = 0;
    
    //switch case:
    final public static int NEEDED_IN_SITE = 0;
    final public static int NEEDED_IN_PAGE = 1;
    final public static int NEEDED_IN_CONTENT = 2;
    final public static int NEEDED_IN_INNER_CONTENT = 3;
    
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