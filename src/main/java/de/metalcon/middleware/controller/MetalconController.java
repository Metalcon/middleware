package de.metalcon.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;

import de.metalcon.middleware.core.UserSession;
import de.metalcon.middleware.core.UserSessionFactory;
import de.metalcon.middleware.view.MetalconView;

public abstract class MetalconController {

    private String metalconNamespace = "metalcon";
    
    public String getMetalconNamespace() {
    	return this.metalconNamespace;
    }
    
    @Autowired UserSessionFactory usf;
    public MetalconView handleRequest(MetalconView mcView){
        UserSession user = usf.getUserSession();
        mcView.setId(user.getId()+"");
        user.incPageCount();
        mcView.setPc(user.getPageCount()+"");
        return mcView;
    }
    
}
