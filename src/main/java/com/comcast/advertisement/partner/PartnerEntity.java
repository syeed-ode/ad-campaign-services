package com.comcast.advertisement.partner;

import com.comcast.advertisement.campaign.dto.CampaignEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Entity(name = "PARTNER")
public class PartnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name = "uuid")
    private String partnerUuid;

    @Column(name = "name")
    private String partnerName;

    @Column(name = "external_id")
    private String externalId;

    @OneToMany(mappedBy = "partnerEntity")
    private Set<CampaignEntity> campaignEntitySet = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartnerUuid() {
        return partnerUuid;
    }

    public void setPartnerUuid(String partnerUuid) {
        this.partnerUuid = partnerUuid;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Set<CampaignEntity> getCampaignEntitySet() {
        return campaignEntitySet;
    }

    public void setCampaignEntitySet(Set<CampaignEntity> campaignEntitySet) {
        this.campaignEntitySet = campaignEntitySet;
    }
}
