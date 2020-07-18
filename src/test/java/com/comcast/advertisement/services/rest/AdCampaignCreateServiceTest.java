package com.comcast.advertisement.services.rest;

import com.comcast.advertisement.AdCampaignBaseTest;
import com.comcast.advertisement.campaign.*;
import com.comcast.advertisement.campaign.dto.CampaignEntity;
import com.comcast.advertisement.controller.AdCampaignCreateRequest;
import com.comcast.advertisement.partner.PartnerEntity;
import com.comcast.advertisement.partner.PartnerRepository;
import com.comcast.advertisement.partner.PartnerService;
import com.comcast.advertisement.utilities.AdCampaingUuidGenerator;
import com.comcast.advertisement.utilities.DateUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.comcast.advertisement.campaign.CampaignStatusEnum.ACTIVE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Ad Service Application
 * <p>
 * Author: syeedode
 * Date: 7/14/17
 */
@RunWith(MockitoJUnitRunner.class)
public class AdCampaignCreateServiceTest implements AdCampaignBaseTest {

    @InjectMocks
    AdCampaignCreateService service;

    @Mock
    AdCampaingUuidGenerator uuidGenerator;

    @Mock
    PartnerRepository partnerRepo;

    @Mock
    CampaignRepository campaignRepo;

    @Mock
    DateUtility dateTime;

    @Mock
    PartnerService patnerService;

    @Before
    public void setUp() {
        // Mockito.when()
    }

    @Test
    public void create_Success() {
        PartnerEntity p = partnerEntityGoPathMocks();
        campaignEntityGoPathMocks();
        when(patnerService.determinePartnerCampainAssociation(any(PartnerEntity.class),any(CampaignEntity.class))).thenReturn(p);
        ResponseEntity<?> response = service.create(validRequest());
        Assert.assertEquals(CREATED, response.getStatusCode());
    }

    private PartnerEntity partnerEntityGoPathMocks() {
        when(partnerRepo.findByExternalId(PARTNER_ID)).thenReturn(null);
        when(uuidGenerator.uuid()).thenReturn(GODD_PART_UUID);
        when(partnerRepo.save(any(PartnerEntity.class))).thenReturn(goodPartnerEntity());

        return service.createPartner(PARTNER_ID);
    }

    private CampaignEntity campaignEntityGoPathMocks() {
        when(campaignRepo.findByCampaignTitle(CAMPAIGN_TITLE)).thenReturn(null);
        when(uuidGenerator.uuid()).thenReturn(GODD_CAMP_UUID);
        when(dateTime.getTimeInEpoch()).thenReturn(CURRENT_TIME.intValue());
        when(campaignRepo.save(any(CampaignEntity.class))).thenReturn(goodCampaignEntity());

        return service.createCampaign(dto());
    }

    @Test
    public void createPartner_useReflection() throws Exception {
        PartnerEntity result = partnerEntityGoPathMocks();
        Method method = AdCampaignCreateService.class.getDeclaredMethod("createPartner", String.class);
        method.setAccessible(true);

        PartnerEntity output = (PartnerEntity) method.invoke(new AdCampaignCreateService(), PARTNER_ID);
        assertNotNull(output);
    }

    @Test
    public void createPartner_whenNoPartner_createAndSaveValidPartner(){
        PartnerEntity result = partnerEntityGoPathMocks();

        verify(partnerRepo,times(1)).save(any(PartnerEntity.class));
        assertPartnerValues(result);
    }

    @Test
    public void createPartner_whenPartnerExists_returnCurrentPartner() {
        when(partnerRepo.findByExternalId(PARTNER_ID)).thenReturn(goodPartnerEntity());

        PartnerEntity result = service.createPartner(PARTNER_ID);
        verify(partnerRepo,times(0)).save(any(PartnerEntity.class));
        verify(uuidGenerator,times(0)).uuid();

        assertPartnerValues(result);
    }

    private void assertPartnerValues(PartnerEntity result) {
        assertEquals(PARTNER_ID, result.getExternalId());
        assertEquals(GODD_PART_UUID, result.getPartnerUuid());
        assertNotNull(result.getId());
    }

    @Test
    public void createCampaign_whenCampaignExists_returnCurrentCampaign(){
        when(campaignRepo.findByCampaignTitle(CAMPAIGN_TITLE)).thenReturn(goodCampaignEntity());

        CampaignEntity result = service.createCampaign(dto());
        verify(partnerRepo,times(0)).save(any(PartnerEntity.class));
        verify(uuidGenerator,times(0)).uuid();
        verify(dateTime,times(0)).getTimeInEpoch();
        verify(campaignRepo,times(0)).save(any(CampaignEntity.class));

        assertCampaignValues(result);
    }

    @Test
    public void createCampaign_whenNoCampaign_createAndSaveValidCampaign(){
        CampaignEntity result = campaignEntityGoPathMocks();

        verify(campaignRepo,times(1)).save(any(CampaignEntity.class));
        assertCampaignValues(result);
    }

    private CampaignDto dto() {
        CampaingDtoBuilder builder = new CampaingDtoBuilder();
        return builder.content(CONTENT)
                .expiration(ACTIVE_TIME)
                .title(CAMPAIGN_TITLE)
                .status(INItiAL_CAMP_STATUS)
                .build();
    }

    private void assertCampaignValues(CampaignEntity result) {
        assertEquals(CONTENT, result.getCampaignContent());
        assertEquals(TITLE, result.getCampaignTitle());
        assertEquals(GODD_CAMP_UUID, result.getCampaignUuid());
        Integer time = dto().getExpirationDateInSeconds() + Integer.valueOf(CURRENT_TIME.intValue());
        assertEquals(time, result.getExpirationDate());
        assertNotNull(result.getId());
    }

    private AdCampaignCreateRequest validRequest() {
        AdCampaignCreateRequest validRequest = new AdCampaignCreateRequest();
        validRequest.setAdContent(CONTENT);
        validRequest.setAdStatus(ACTIVE.valueOf());
        validRequest.setExternalPartnerId(PARTNER_ID);
        validRequest.setAdTitle(TITLE);
        validRequest.setDuration(ACTIVE_TIME);
        return validRequest;
    }

}