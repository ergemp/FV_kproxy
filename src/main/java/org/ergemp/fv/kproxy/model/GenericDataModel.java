package org.ergemp.fv.kproxy.model;

public class GenericDataModel {
    private String token;
    private Long ts;
    private String label;
    private String payload;

    public GenericDataModel(String gToken, Long gTs, String gLabel, String gPayload){
        this.token = gToken;
        this.ts = gTs;
        this.label = gLabel;
        this.payload = gPayload;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
