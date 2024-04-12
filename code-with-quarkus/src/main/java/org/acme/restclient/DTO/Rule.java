package org.acme.restclient.DTO;

import java.util.List;

public class Rule {
    public long ruleID;
    public AssetRule asset;
    public List<Fence> fences;
}
