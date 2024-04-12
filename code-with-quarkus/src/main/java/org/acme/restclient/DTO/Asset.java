package org.acme.restclient.DTO;

import org.acme.restclient.DTO.enums.AssetStatus;
import org.acme.restclient.DTO.enums.MovementStatus;
public class Asset {

    public long assetID;

    public Double longitude;

    public Double latitude;

    public AssetStatus status;

    public MovementStatus movementStatus;

}
