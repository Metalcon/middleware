package de.metalcon.middleware.core;

import javax.servlet.http.HttpServletRequest;

import com.iekadou.pjaxr.Pjaxr;

public class MetalconPjaxr extends Pjaxr {

    private boolean pjaxrSite = true;

    private boolean pjaxrPage = true;

    private boolean pjaxrContent = true;

    private boolean pjaxrInnerContent = true;

    public MetalconPjaxr(
            HttpServletRequest request,
            String currentNamespace) {
        super(request, currentNamespace);
        pjaxrSite = matchingCount <= 0;
        pjaxrPage = matchingCount <= 1;
        pjaxrContent = matchingCount <= 2;
        pjaxrInnerContent = matchingCount <= 3;
    }

    public boolean isPjaxrSite() {
        return pjaxrSite;
    }

    public boolean isPjaxrPage() {
        return pjaxrPage;
    }

    public boolean isPjaxrContent() {
        return pjaxrContent;
    }

    public boolean isPjaxrInnerContent() {
        return pjaxrInnerContent;
    }

}
