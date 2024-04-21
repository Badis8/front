package org.acme;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import io.quarkiverse.renarde.Controller;
import org.acme.restclient.DTO.RuleToSend;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.acme.model.AssetPresentationWithinRules;
import org.acme.model.AssetWithDetails;
import org.acme.restclient.ExtensionsService;
import org.acme.restclient.FencesService;
import org.acme.restclient.RuleService;
import org.acme.restclient.AssetDetailsService;
import org.acme.restclient.DTO.Asset;
import org.acme.restclient.DTO.AssetDetails;
import org.acme.restclient.DTO.Fence;
import org.acme.restclient.DTO.Rule;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.annotations.Pos;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Application extends Controller {
    @RestClient
    @Inject
    ExtensionsService extensionsService;
    @RestClient
    @Inject
    FencesService FencesService;
    @Inject
    @RestClient
    AssetDetailsService assetDetailService;
    @Inject
    @RestClient
    RuleService ruleService;

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<AssetWithDetails> assets, String assetJSON,List<Fence> fences,String fenceJSON);


        public static native TemplateInstance AssetList(List<AssetDetails> AssetDetails);



        
        public static native TemplateInstance AssetDetail(AssetPresentationWithinRules d,String assetJSON);

        public static native TemplateInstance AddAsset();

        public static native TemplateInstance FenceDetail(Fence d); 


        public static native TemplateInstance FenceList(List<Fence> Fences);


        public static native TemplateInstance addFence(); 

        public static native TemplateInstance ruleDetails(Rule r,String rJSON,List<Fence> unIncludedFences,String unIncludedJson);

        public static native TemplateInstance rules(List<Rule> rules,String rulesJson); 

        public static native TemplateInstance addRule(List<Fence> fences,List<AssetDetails> assets);

        public static native TemplateInstance homePage();
        
    }

    @Blocking
    @Path("/")
    public TemplateInstance index() throws Exception {

        List<Asset> assets = extensionsService.findAllAssets();
       // ObjectMapper om = new ObjectMapper();
       // String assetJSON = om.writeValueAsString(assets);
        List<Fence> fences = FencesService.findAllFences();
        ObjectMapper omF = new ObjectMapper();
        String fenceJSON = omF.writeValueAsString(fences);

        List<AssetDetails> AssetDetails = assetDetailService.findAllAssets();
        for (AssetDetails assetDetail : AssetDetails) {
            System.out.println("Asset ID: " + assetDetail.assetId); 
            System.out.println("description: " + assetDetail.description);
            System.out.println("Longitude: " + assetDetail.tag);
    
          }
          
        List<AssetWithDetails> assetsWithDetails=AssetWithDetails.combineLists(assets,AssetDetails);
        ObjectMapper om = new ObjectMapper();
        String assetJSON = om.writeValueAsString(assetsWithDetails);

        return Templates.index(assetsWithDetails, assetJSON,fences,fenceJSON);
    }  





    @Blocking
    @Path("/AssetList")
    public TemplateInstance AssetList() throws Exception {
        List<AssetDetails> AssetDetails = assetDetailService.findAllAssets();   
        
        return Templates.AssetList(AssetDetails);
    }  


    @Blocking
    @Path("/AssetDetail/{id}")
    public TemplateInstance AssetDetail(@PathParam("id") Long id) throws Exception {
        AssetDetails AssetDetails = assetDetailService.findAssetById(id);
        Asset assetCoordinate= extensionsService.findAssetById(id); 
        List<Rule> assetRule=ruleService.findByAssetID(id);
        for (Rule assetRul : assetRule) {
            System.out.println("Asset ID: " + assetRul.ruleID); 
            System.out.println("description: " + assetRul.fences.size());
          
          }
        AssetPresentationWithinRules assetProfile=new AssetPresentationWithinRules(AssetDetails,assetRule,assetCoordinate);
         
        ObjectMapper om = new ObjectMapper();
        String assetJSON = om.writeValueAsString(assetProfile);
        return  Templates.AssetDetail(assetProfile,assetJSON);
        

 
    }  

    @POST
    @Path("/assets/{id}")
    public void  updateAsset(@PathParam("id") Long id,@FormParam("status") String status,@FormParam("tenantOwner") String tenantOwner,@FormParam("description") String description,@FormParam("tag") String tag,@FormParam("type") String type) throws Exception          {
        AssetDetails ad=new AssetDetails(id,tenantOwner,type,tag,description,status);
        this.assetDetailService.updateAsset(ad);
        AssetDetail(id) ;
    
            
        
    } 


    @Blocking
    @Path("/AddAsset")
    public TemplateInstance AddAsset() throws Exception {
        return Templates.AddAsset();
    }  


    @POST
    @Path("/AddAsset")
    public void  AddAsset(@FormParam("status") String status,@FormParam("tenantOwner") String tenantOwner,@FormParam("description") String description,@FormParam("tag") String tag,@FormParam("type") String type) throws Exception          {
        AssetDetails ad=new AssetDetails(0,tenantOwner,type,tag,description,status);
        
        this.assetDetailService.addAsset(ad);
        
        AssetList();
            
        
    } 

    
    @Path("/DeleteAsset/{id}")
    public void  deleteAsset(@PathParam("id") Long id) throws Exception          {
        this.assetDetailService.deleteAsset(id);
        AssetList();   
    }  

    @Blocking
    @Path("/fenceDetail/{id}")
    public TemplateInstance  fenceDetail(@PathParam("id") Long id) throws Exception          {
        Fence fenceDetail=this.FencesService.findFenceById(id);

        return Templates.FenceDetail(fenceDetail);
        
    } 
    @Blocking
    @POST
    @Path("/fences/{id}")
    public void  updateFence(@PathParam("id") Long id,@FormParam("longitude") String longitude,@FormParam("radius") String radius,@FormParam("description") String description,@FormParam("tag") String tag,@FormParam("latitude") String latitude) throws Exception          {
        Fence toUpdate=new Fence(id,Double.parseDouble(latitude),Double.parseDouble(longitude),Double.parseDouble(radius),tag,description);
        this.FencesService.updateFence(toUpdate);
        fenceDetail(id);
        
    }  



    @Blocking
    @Path("/Fences")
    public TemplateInstance fenceList() throws Exception {
        List<Fence> fenceList = this.FencesService.findAllFences();   

        return Templates.FenceList(fenceList);
    }  
    @Path("/DeleteFence/{id}")
    public void  deleteFence(@PathParam("id") long id) throws Exception          {
        this.FencesService.deleteFence(id);
        fenceList();   
    }  

    @Path("/AddFence")
    public TemplateInstance  addFence( ) throws Exception          {
        return Templates.addFence(); 
       }  

       @Blocking
       @POST
       @Path("/Addfences")
       public void  addFence(@FormParam("longitude") String longitude,@FormParam("radius") String radius,@FormParam("description") String description,@FormParam("tag") String tag,@FormParam("latitude") String latitude) throws Exception          {
           Fence toAdd=new Fence(0,Double.parseDouble(latitude),Double.parseDouble(longitude),Double.parseDouble(radius),tag,description);
           this.FencesService.saveFence(toAdd); 
           fenceList(); 
       }  
       @Blocking
       @Path("/rule/{id}")
       public TemplateInstance  ruleDetail(@PathParam("id") Long id) throws Exception          {
        Rule r=this.ruleService.findByID(id); 
        List<Fence> allFences=this.FencesService.findAllFences();
        Iterator<Fence> iterator = allFences.iterator();
        while (iterator.hasNext()) {
            Fence fence = iterator.next();
            if (Fence.containsFence(r.fences, fence)) {
                iterator.remove();
            }
        }
        ObjectMapper om = new ObjectMapper();
        String rJSON = om.writeValueAsString(r);
        String unIncluded=om.writeValueAsString(allFences);
        return Templates.ruleDetails(r,rJSON,allFences,unIncluded);              
       }   
       @Blocking
       @Path("/deleteFenceFromRule/{id}/{fenceID}")
       public void  deleteFence(@PathParam("id") Long id,@PathParam("fenceID") Long fenceID) throws Exception {
        this.ruleService.removeFenceFromRule(id, fenceID);
        ruleDetail(id);
}   
@Blocking
@Path("/addFenceToRule/{id}/{fenceID}")
public void  addFence(@PathParam("id") Long id,@PathParam("fenceID") Long fenceID) throws Exception{    
    this.ruleService.addFence(id, fenceID);
    ruleDetail(id);
}            
@Blocking
@Path("/Rules")
public TemplateInstance  getAllRules() throws Exception{    
   List<Rule> rules=this.ruleService.findAllRules();
   ObjectMapper om = new ObjectMapper();
   String rJSON = om.writeValueAsString(rules);
   
   return Templates.rules(rules, rJSON);
   


}    

@Blocking
@Path("/deleteRule/{id}")
public void  deleteRule(@PathParam("id") Long id) throws Exception{    
    this.ruleService.deleteRule(id);
    getAllRules();

}     

@Blocking
@Path("/AddRule")
public TemplateInstance  addRule() throws Exception{    
    List<Fence> allFences=this.FencesService.findAllFences(); 
    List<AssetDetails> AssetDetails = assetDetailService.findAllAssets();
   return  Templates.addRule(allFences, AssetDetails);
}   

@Blocking
@POST
@Path("/AddRule")
public void addRules(@FormParam("description") String description,@FormParam("tag") String tag,@FormParam("Fences") List<Long> fences,@FormParam("Asset") Long asset) throws Exception{    
    RuleToSend newRule=new RuleToSend(asset,fences,tag,description);
    this.ruleService.addRule(newRule);
    getAllRules();
} 



@Blocking
@Path("/home")
public TemplateInstance home() throws Exception {
    return Templates.homePage();
}
}