create table TB_Patient
(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    telephone varchar(40) not null,
    document varchar(40) not null unique,
    publicPlace varchar(100) not null,
    neighborhood varchar(100) not null,
    zipCode varchar(9) not null,
    complement varchar(100),
    number varchar(20),
    state char(2) not null,
    city varchar(100) not null,
    primary key (id)
);