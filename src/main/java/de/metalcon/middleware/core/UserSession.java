package de.metalcon.middleware.core;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * user session holding all relevant data from user and his social network
 */
public class UserSession {
    int id=-1;
    int pagecount = 0;
    
    public int getId(){
        if (id<0){
            id = (int)(Math.random()*100);
        }
        return id;
    }
    
    public int getPageCount(){
        return pagecount;
    }
    
    public void incPageCount(){
        pagecount++;
    }

}
