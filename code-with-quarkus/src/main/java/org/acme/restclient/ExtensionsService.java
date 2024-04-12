package org.acme.restclient;

import org.acme.restclient.DTO.Asset;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.Set;

 
@RegisterRestClient(configKey = "asset-api")
public interface ExtensionsService {
    @Path("/assets")
    @GET
    List<Asset> findAllAssets();
    @Path("/assets/{id}")
    @GET
    Asset findAssetById(@PathParam("id") Long id); 

}
