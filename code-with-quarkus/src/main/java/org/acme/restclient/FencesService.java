package org.acme.restclient;
import org.acme.restclient.DTO.Asset;
import org.acme.restclient.DTO.Fence;
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

 
@RegisterRestClient(configKey = "fence-api")
public interface FencesService {
    @Path("/fences")
    @GET
    List<Fence> findAllFences();
    @Path("/fences/{id}")
    @GET
    Fence findFenceById(@PathParam("id") Long id) ;
    @Path("/fences")
    @PUT
    Fence updateFence(Fence fence) ; 
    @Path("/fences")
    @DELETE
    void deleteFence(long id) ; 
    @Path("/fences")
    @POST
    void saveFence(Fence fence);
}
