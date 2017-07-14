create table CAMPAIGN (
    ID int not null,
    UUID varchar(40) not null,
    CONTENT varchar(100),
    TITLE varchar(100) not null,
    STATUS varchar(30),
    EXPIRATION_IN_EPOCH int not null,
    primary key (ID)
);

create unique index TITLE_IDX on CAMPAIGN (TITLE) using HASH;