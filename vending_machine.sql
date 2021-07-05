create table Card
(
    name   varchar(50) not null,
    number int         not null
        primary key
);

create table Category
(
    category_name varchar(50) not null,
    category_id   int auto_increment
        primary key,
    parent_id     int         null,
    constraint Category_Category_category_id_fk
        foreign key (parent_id) references Category (category_id)
            on update cascade on delete cascade
);

create table Commodity
(
    commodity_id int           not null
        primary key,
    name         varchar(50)   not null,
    price        double        not null,
    sold_number  int default 0 not null,
    quantity     int default 7 not null,
    category     int           null,
    pic_address  varchar(255)  null,
    constraint Commodity_Category_category_id_fk
        foreign key (category) references Category (category_id)
            on update cascade on delete cascade
);

create table Money
(
    money_id int auto_increment,
    value    double        not null,
    quantity int default 5 not null,
    constraint cash_cash_id_uindex
        unique (money_id)
);

alter table Money
    add primary key (money_id);

create table Role
(
    role_id   int auto_increment
        primary key,
    role_name varchar(10) not null,
    constraint Role_role_name_uindex
        unique (role_name)
);

create table Transaction
(
    transaction_id int auto_increment
        primary key,
    user_id        int                  not null,
    payment        tinyint(1)           not null,
    status         tinyint(1) default 0 null,
    time           datetime             not null,
    price          double               not null,
    `change`       double               null,
    amount         double               null
);

create table `Order`
(
    id             int auto_increment
        primary key,
    quantity       int not null,
    commodity_id   int not null,
    transaction_id int not null,
    constraint Order_Commodity_commodity_id_fk
        foreign key (commodity_id) references Commodity (commodity_id)
            on update cascade on delete cascade,
    constraint Order_Transaction_transaction_id_fk
        foreign key (transaction_id) references Transaction (transaction_id)
            on update cascade on delete cascade
);

create index Order_Customer_customer_id_fk
    on Transaction (user_id);

create table User
(
    user_id  int auto_increment
        primary key,
    salt     varchar(50)   not null,
    username varchar(50)   not null,
    password varchar(50)   not null,
    card     int           null,
    role     int default 3 null,
    constraint User_Role_role_id_fk
        foreign key (role) references Role (role_id)
            on update cascade on delete cascade
);

create index User_Card_number_fk
    on User (card);

create index User_Owner_account_fk
    on User (username);

create table User_Commodity
(
    commodity_id int not null,
    user_id      int not null,
    id           int auto_increment
        primary key,
    constraint User_Commodity_Commodity_commodity_id_fk
        foreign key (commodity_id) references Commodity (commodity_id)
            on update cascade on delete cascade,
    constraint User_Commodity_User_user_id_fk
        foreign key (user_id) references User (user_id)
            on update cascade on delete cascade
);


