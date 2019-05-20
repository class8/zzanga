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
f_number varchar2,
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

desc account;

select *
from account;

create table customer(
c_number number,
c_name varchar2(15) not null,
c_cname varchar2(15) not null,
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

