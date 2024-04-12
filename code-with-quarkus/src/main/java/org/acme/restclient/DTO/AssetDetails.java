package org.acme.restclient.DTO;
import java.time.Instant;
import java.util.Date;
public class AssetDetails {

    public long assetId;

    public String tenantOwner;

    public String type;

    public String tag;

    public String description;

    public String status;

    public AssetDetails(long id,String t,String type,String tag,String description,String status){
        this.assetId=id;
        this.tenantOwner=t;
        this.type=type;
        this.tag=tag;
        this.description=description;
        this.status=status;
    }
}
