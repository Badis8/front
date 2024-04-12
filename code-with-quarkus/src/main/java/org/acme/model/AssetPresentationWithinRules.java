package org.acme.model;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.acme.restclient.DTO.Asset;
import org.acme.restclient.DTO.Fence;
import org.acme.restclient.DTO.Rule;
import org.acme.restclient.DTO.enums.AssetStatus;
import org.acme.restclient.DTO.enums.MovementStatus;
import org.acme.restclient.DTO.AssetDetails;
import org.acme.restclient.DTO.AssetRule;
public class AssetPresentationWithinRules {
    public Long id;
    public List<Fence> fence; 
    public Double longitude;
    public Double latitude;
    public AssetStatus status;
    public MovementStatus movementStatus;
    public String tenantOwner;
    public String tag;
    public String description;
    public List<Long> ListOfRules; 
    public String type;

    public AssetPresentationWithinRules(AssetDetails assetWithDetails, List<Rule> rules,Asset asset) {
        this.id=asset.assetID;
        this.longitude = asset.longitude;
        this.latitude = asset.latitude;
        this.status = asset.status;
        this.movementStatus = asset.movementStatus;
        this.tenantOwner = assetWithDetails.tenantOwner;
        this.tag = assetWithDetails.tag;
        this.description=assetWithDetails.description;
        this.type=assetWithDetails.type;
        this.ListOfRules=new ArrayList<>();
        Set<Fence> uniqueFences = new HashSet<>();
        
        for (Rule rule : rules) {
            
            uniqueFences.addAll(rule.fences);  
            this.ListOfRules.add(rule.ruleID);
        }
        this.fence = new ArrayList<>(uniqueFences);  
 
    }
}
