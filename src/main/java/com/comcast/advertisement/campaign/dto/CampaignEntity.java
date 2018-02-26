package com.comcast.advertisement.campaign.dto;

import com.comcast.advertisement.campaign.CampaignStatusEnum;
import com.comcast.advertisement.partner.PartnerEntity;

import javax.persistence.*;
import java.util.Objects;

import static com.comcast.advertisement.campaign.CampaignStatusEnum.INACTIVE;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */
@Entity(name = "CAMPAIGN")
public class CampaignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name = "uuid")
    private String campaignUuid;

    @Column(name = "content")
    private String campaignContent;

    @Column(name = "title")
    private String campaignTitle;

    @Column(name = "status")
    private CampaignStatusEnum campaignStatus = INACTIVE;

    @Column(name = "expiration_in_epoch")
    private Integer expirationDate;

    @ManyToOne
    @JoinTable(name="CAMPAIGN_PARTNER",
            joinColumns = @JoinColumn(name = "CAMPAIGN_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PARTNER_ID", referencedColumnName = "ID"))
    private PartnerEntity partnerEntity;

    public CampaignEntity() {

    }

    protected CampaignEntity(CampaignBuilder builder) {
        campaignUuid    = builder.getCompaignUuid();
        campaignContent = builder.getCampaignContent();
        campaignStatus  = builder.getCampaignStatus();
        campaignTitle   = builder.getCampaignTitle();
        expirationDate  = builder.getExpirationDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampaignUuid() {
        return campaignUuid;
    }

    public void setCampaignUuid(String campaignUuid) {
        this.campaignUuid = campaignUuid;
    }

    public String getCampaignContent() {
        return campaignContent;
    }

    public void setCampaignContent(String campaignContent) {
        this.campaignContent = campaignContent;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public CampaignStatusEnum getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(CampaignStatusEnum campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public Integer getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PartnerEntity getPartnerEntity() {
        return partnerEntity;
    }

    public void setPartnerEntity(PartnerEntity partnerEntity) {
        this.partnerEntity = partnerEntity;
    }

    @Override
    public boolean equals(Object o){
        CampaignEntity that = (CampaignEntity) o;
        boolean uuidsMatch = (Objects.isNull(this.getCampaignUuid()) && Objects.isNull(that.getCampaignUuid()))
                || this.getCampaignUuid().equals(that.getCampaignUuid());

        boolean titlesMatch = (Objects.isNull(this.getCampaignTitle()) && Objects.isNull(that.getCampaignTitle()))
                || (this.getCampaignTitle().equals(that.getCampaignTitle()));

        boolean idsMatch = (Objects.isNull(this.getId()) && Objects.isNull(that.getId()))
                || (this.getId().equals(that.getId()));

        return uuidsMatch && titlesMatch && idsMatch;
    }

    @Override
    public int hashCode(){
        int result = this.getId();
        result = 31 * result + this.getCampaignUuid().hashCode();
        result = 31 * result + this.getCampaignTitle().hashCode();
        return result;
    }
}
