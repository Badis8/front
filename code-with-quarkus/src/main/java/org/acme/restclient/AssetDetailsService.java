package org.acme.restclient;

import org.acme.restclient.DTO.AssetDetails;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.Set;


@RegisterRestClient(configKey = "asset-details-api")
public interface AssetDetailsService {
    @Path("/assets")
    @GET
    List<AssetDetails> findAllAssets();
    @Path("/assets/{id}")
    @GET
    AssetDetails findAssetById(@PathParam("id") Long id);   
    @PUT
    @Path("/assets")
    AssetDetails updateAsset(AssetDetails a);  
    @POST
    @Path("/assets")
    AssetDetails addAsset(AssetDetails a); 
    @DELETE
    @Path("/assets/{id}")
    AssetDetails deleteAsset(@PathParam("id") Long id); 
}
