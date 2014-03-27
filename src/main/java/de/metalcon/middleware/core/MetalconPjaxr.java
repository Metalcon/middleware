package de.metalcon.middleware.core;

import javax.servlet.http.HttpServletRequest;

import com.iekadou.pjaxr.Pjaxr;

public class MetalconPjaxr extends Pjaxr {
    public boolean pjaxr_site = true;
    public boolean pjaxr_page = true;
    public boolean pjaxr_content = true;
    public boolean pjaxr_inner_content = true;

    public MetalconPjaxr(HttpServletRequest request, String currentNamespace) {
        super(request, currentNamespace);
        this.pjaxr_site = this.matchingCount <= 0;
        this.pjaxr_page = this.matchingCount <= 1;
        this.pjaxr_content = this.matchingCount <= 2;
        this.pjaxr_inner_content = this.matchingCount <= 3;
    }
}
