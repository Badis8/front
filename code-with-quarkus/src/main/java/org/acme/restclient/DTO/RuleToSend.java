package org.acme.restclient.DTO;

import java.util.ArrayList;
import java.util.List;

public class RuleToSend {
    public AssetToSendForRule asset; 
    public List<FenceToSendForRule> fences; 
    public String description; 
    public String tag; 
    public RuleToSend(Long assetId,List<Long> fencesID,String tag,String description){
            this.asset=new AssetToSendForRule(assetId); 
            this.fences=new ArrayList<>();
            for(Long f:fencesID){
                FenceToSendForRule toAdd=new FenceToSendForRule(f);
                fences.add(toAdd);
            } 
            this.description=description;
            this.tag=tag;
    }
} 
