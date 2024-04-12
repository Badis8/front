package org.acme.restclient;
import org.acme.restclient.DTO.Asset;
import org.acme.restclient.DTO.Fence;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.Set;

@Path("/fences")
@RegisterRestClient(configKey = "fence-api")
public interface FencesService {

    @GET
    List<Fence> findAllFences();


 

}
