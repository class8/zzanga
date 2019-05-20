select *
from tab;


create table administrator(
id varchar2(20),
name varchar2(20) not null,
password varchar2(20) not null,
primary key(id)
);

drop table administrator;

insert into administrator(id,name,password)
VALUES ('admin','admin','admin');

select *
from administrator;

drop table account;

create table account(
a_number number,
a_cname varchar2(15) not null,
a_mname varchar2(15) not null,
a_phone varchar2(15) not null,
a_email varchar2(50) not null,
a_address varchar2(100) not null,
a_bnumber varchar2(20) unique not null,
a_msubject varchar2(20) not null,
a_remarks varchar2(50),
a_registdate date DEFAULT sysdate not null,
primary key(a_number)   
);

drop sequence account_seq;

create sequence account_seq
start with 1
increment by 1;

select account_seq.nextval from dual;

create table fabric(
f_number number,
f_sort varchar2(10) not null,
f_name varchar2(20) not null,
f_color varchar2(10) not null,
f_size number(5) not null,
f_material varchar2(20) not null,
f_origin varchar2(10) not null,
f_cname varchar2(15) not null,
f_phone varchar2(15) not null,
f_weight varchar2(10) not null,
f_trait varchar2(50),
f_price varchar2(15) not null,
f_remarks varchar2(50),
a_registdate date DEFAULT sysdate not null,
FILENAME     VARCHAR2(100)  not null,
primary key(f_number)  
);

create sequence fabric_seq
start with 1
increment by 1;

desc account;

select *
from account;

drop table customer;

create table customer(
c_number number,
c_name varchar2(15) not null,
c_cname varchar2(15),
c_phone varchar2(15) not null,
c_email varchar2(50),
c_address varchar2(100),
c_bnumber varchar2(20) unique,
c_remarks varchar2(50),
c_registdate date DEFAULT sysdate not null,
primary key(c_number)   
);

create sequence customer_seq
start with 1
increment by 1;

create table trade(
t_number number,
f_number number not null,
c_number number not null,
t_amount number(5) not null,
t_price number(15) not null,
t_deposit number(15) not null,
t_penalty number(15) not null,
t_balance number(15) not null,
t_receipt number(15) not null,
t_unpaid number(15) not null,
t_status varchar2(8) not null,
t_registdate date DEFAULT sysdate not null,
t_address varchar2(100) not null,
t_remarks varchar2(50),
primary key(t_number),
foreign key(f_number) references fabric(f_number),
foreign key(c_number) references customer(c_number)
);

create sequence trade_seq
start with 1
increment by 1;

select *
from trade;
