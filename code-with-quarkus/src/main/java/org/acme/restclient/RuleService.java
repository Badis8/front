package org.acme.restclient;
import org.acme.restclient.DTO.Asset;
import org.acme.restclient.DTO.Fence;
import org.acme.restclient.DTO.Rule;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.Set;
@RegisterRestClient(configKey = "rule-api")
public interface RuleService {
    @Path("/rules/{id}")
    @GET
    List<Rule> findByAssetID(@PathParam("id") Long id);
}
