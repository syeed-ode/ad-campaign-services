create table CAMPAIGN_PARTNER
(
  ID int NOT NULL AUTO_INCREMENT,
  CAMPAIGN_ID int NOT NULL,
  PARTNER_ID int NOT NULL,

  PRIMARY KEY (ID),
  UNIQUE INDEX (PARTNER_ID, CAMPAIGN_ID),
  foreign key (CAMPAIGN_ID) references CAMPAIGN(ID),
  foreign key (PARTNER_ID) references PARTNER(ID)
);
