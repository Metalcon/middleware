package de.metalcon.middleware.controller;

public abstract class MetalconController {

    private String metalconNamespace = "metalcon";
    
    public String getMetalconNamespace() {
    	return this.metalconNamespace;
    }
}
