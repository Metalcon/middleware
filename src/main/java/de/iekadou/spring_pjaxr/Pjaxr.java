package de.iekadou.spring_pjaxr;

import javax.servlet.http.HttpServletRequest;

public class Pjaxr {

	private String currentNamespace;
	private String previousNamespace = "";
	private String matchingNamespace = "";
	private int matchingCount = 0;
	
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
	    				if (level > 0) {
	    					matchedNamespace = matchedNamespace + ".";
	    				}
	    				matchedNamespace = matchedNamespace + currentNamespaces[level];
	            		this.matchingCount = level;
	    			}
    			}
    		}
    		this.matchingNamespace = matchedNamespace;
    	}
	}

	public String getCurrentNamespace() {
		return currentNamespace;
	}
	public String getPreviousNamespace() {
		return previousNamespace;
	}

	public String getMatchingNamespace() {
		return matchingNamespace;
	}

	public int getMatchingCount() {
		return matchingCount;
	}
}
