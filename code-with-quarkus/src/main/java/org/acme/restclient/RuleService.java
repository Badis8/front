package org.acme.restclient;
import org.acme.restclient.DTO.Asset;
import org.acme.restclient.DTO.Fence;
import org.acme.restclient.DTO.Rule;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.acme.restclient.DTO.RuleToSend;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
    @Path("/rules/rule/{id}")
    @GET
    Rule findByID(@PathParam("id") Long id);
    @Path("/rules/removeFence/{id}/{fenceID}")
    @DELETE
    Rule removeFenceFromRule(@PathParam("id") Long id,@PathParam("fenceID") Long fenceID);
    @Path("/rules/addFence/{id}/{fenceID}")
    @POST
    Rule addFence(@PathParam("id") Long id,@PathParam("fenceID") Long fenceID);
    @DELETE
    @Path("/rules/{RuleID}")
    void deleteRule(@PathParam("RuleID") long id);
    @GET
    @Path("/rules")
    List<Rule> findAllRules();
    @POST
    @Path("/rules")
    Rule addRule(RuleToSend e);
}
