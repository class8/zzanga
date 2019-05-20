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


