create table service_order (
    id bigint not null auto_increment,
    costumer_id bigint not null,
    description text not null,
    price decimal(10,2) not null,
    status varchar(20) not null,
    opening_date datetime not null,
    closing_date datetime,

    primary key (id)
);

alter table service_order add constraint fk_service_order_costumer
foreign key (costumer_id) references costumer (id);