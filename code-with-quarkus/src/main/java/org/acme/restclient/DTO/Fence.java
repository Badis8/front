package org.acme.restclient.DTO;
public class Fence {
 
    public long fenceId;
 
    public Double latitude;

 
    public Double longitude;
  
    
    public Double radius; 

    public String tag;

    public String description; 

    public Fence(long fenceId,Double latitude,Double longitude,Double radius,String tag,String description){
        this.fenceId=fenceId;
        this.latitude=latitude;
        this.longitude=longitude;
        this.radius=radius;
        this.description=description;
        this.tag=tag;
    }

}
