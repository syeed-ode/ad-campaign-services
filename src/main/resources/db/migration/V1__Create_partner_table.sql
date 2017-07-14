create table PARTNER (
    ID int not null,
    UUID varchar(40) not null,
    NAME varchar(100),
    EXTERNAL_ID varchar(40) not null,
    primary key (ID)
);

create unique index UUID_IDX on PARTNER (UUID) using HASH;