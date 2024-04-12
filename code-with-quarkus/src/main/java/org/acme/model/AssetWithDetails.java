package org.acme.model;
import java.time.Instant;
import org.acme.restclient.DTO.Asset;
import org.acme.restclient.DTO.Fence;
import org.acme.restclient.DTO.enums.AssetStatus;
import org.acme.restclient.DTO.enums.MovementStatus;
import org.acme.restclient.DTO.AssetDetails;

import java.util.ArrayList;
import java.util.List;
public class AssetWithDetails {
    public long assetId;
    public Double longitude;
    public Double latitude;
    public AssetStatus status;
    public MovementStatus movementStatus;
    public String tenantOwner;
    public String tag;
    public String type;

        public AssetWithDetails(Asset asset, AssetDetails details) {
        this.assetId = asset.assetID;
        this.longitude = asset.longitude;
        this.latitude = asset.latitude;
        this.status = asset.status;
        this.movementStatus = asset.movementStatus;
        this.type=details.type;
 
        this.tenantOwner = details.tenantOwner;

        this.tag = details.tag;

    }

     public static List<AssetWithDetails> combineLists(List<Asset> assets, List<AssetDetails> details) {
        List<AssetWithDetails> combinedList = new ArrayList<>();
        
        for (Asset asset : assets) { 
            if(asset.latitude != -200){
            for (AssetDetails detail : details) {
                if (asset.assetID == detail.assetId) {
                    combinedList.add(new AssetWithDetails(asset, detail));
                    break; 
                }
            }
        }
        }

      
        return combinedList;
    }
}
 