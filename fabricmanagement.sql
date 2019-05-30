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

drop table account;

create table account(
a_number number,
a_cname varchar2(30) not null,
a_mname varchar2(30) not null,
a_phone varchar2(30) not null,
a_email varchar2(100) not null,
a_address varchar2(200) not null,
a_bnumber varchar2(40) unique not null,
a_msubject varchar2(40) not null,
a_remarks varchar2(100),
a_registdate date DEFAULT sysdate not null,
primary key(a_number)   
);

drop sequence account_seq;

create sequence account_seq
start with 1
increment by 1;

select account_seq.nextval from dual;

create table fabric(
f_number varchar2(20),
f_sort varchar2(20) not null,
f_name varchar2(40) not null,
f_color varchar2(20) not null,
f_size number(5) not null,
f_material varchar2(40) not null,
f_origin varchar2(20) not null,
f_cname varchar2(30) not null,
f_phone varchar2(30) not null,
f_weight varchar2(20) not null,
f_trait varchar2(100),
f_price varchar2(30) not null,
f_remarks varchar2(100),
f_registdate date DEFAULT sysdate not null,
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
c_name varchar2(30) not null,
c_cname varchar2(30),
c_phone varchar2(30) not null,
c_email varchar2(100),
c_address varchar2(200),
c_bnumber varchar2(40) unique,
c_remarks varchar2(100),
c_registdate date DEFAULT sysdate not null,
primary key(c_number)   
);

create sequence customer_seq
start with 1
increment by 1;

create table trade(
t_number number,
f_number varchar2(20) not null,
c_number number not null,
t_amount number(10) not null,
t_price number(15) not null,
t_deposit number(15) DEFAULT 0,
t_penalty number(15) DEFAULT 0,
t_balance number(15) DEFAULT 0,
t_receipt number(15) DEFAULT 0,
t_unpaid number(15) DEFAULT 0,
t_status varchar2(16) DEFAULT '확인준비',
t_registdate date DEFAULT sysdate not null,
t_address varchar2(200) not null,
t_remarks varchar2(100),
primary key(t_number),
foreign key(f_number) references fabric(f_number),
foreign key(c_number) references customer(c_number)
);

select * from account;

select a_number from account a, fabric f where f_number

select t_number,f_number,c.c_number,c_name,t_amount,t_price,t_deposit,t_penalty,t_balance,t_receipt,t_unpaid,t_status,c_phone,t_registdate,t_address,t_remarks from trade t, customer c where t.c_number=c.c_number;

select t_number,f_number,c.c_number,c_name,t_amount,t_price,t_deposit,t_penalty,t_balance,t_receipt,t_unpaid,t_status,c_phone,t_registdate,t_address,t_remarks from trade t, customer c where t.c_number=c.c_number and c_name like ? order by t_number desc;

select t_number,f_number,c.c_number,c_name,t_amount,t_price,t_deposit,t_penalty,t_balance,t_receipt,t_unpaid,t_status,c_phone,t_registdate,t_address,t_remarks from trade t, customer c where t.c_number=c.c_number;

insert into trade values(trade_seq.nextval,1,12,5,10,10,10,10,10,10,'거래중2',sysdate,'너네집3','기아아아타3');

commit;

commit;
select *
from customer;

desc trade;

create sequence trade_seq
start with 1
increment by 1;

select *
from order1;

commit;

select trade_seq.nextval
from dual;

select t_number,f_number,c.c_number,c_name,t_amount,t_price,t_deposit,t_penalty,t_balance,t_receipt,t_unpaid,t_status,c_phone,t_registdate,t_address,t_remarks from trade t, customer c where t.c_number=c.c_number;
select * from trade order by t_number;


select t_number, f_number, c.c_number, c_name, t_amount, t_price, t_deposit, t_penalty, t_balance, t_receipt, t_unpaid, t_status, c_phone, t_registdate, t_address, t_remarks from trade t, customer c where t.c_number=c.c_number;

create table order1(
o_number number,    
a_number number not null,
f_number varchar2(20) not null,
c_number number not null,
o_email varchar2(100),
o_address varchar2(200) not null,
o_amount number(40) DEFAULT 0 not null,
o_total number(40) DEFAULT 0 not null,
o_status varchar2(20) DEFAULT '주문시작' not null,
o_registdate date DEFAULT sysdate not null,
o_remarks varchar2(100),
primary key(o_number),
foreign key(f_number) references fabric(f_number),
foreign key(c_number) references customer(c_number),
foreign key(a_number) references account(a_number)
);
desc or

drop table order1;

create sequence order_seq
start with 1
increment by 1;

select o_number,o.a_number,f_number,o_name,o_phone,o_address,o_amount,o_price,o_status,o_registdate,o_remarks,a_email from order1 o, account a where o.a_number=a.a_number;

select t_number, f.f_number, c.c_number, c_name, t_amount, t_price, t_deposit, t_penalty, t_balance, t_receipt, t_unpaid, t_status, c_phone, t_registdate, t_address, t_remarks,f_sort,f_name,f_color,f_size,f_weight,f_price,f_phone from trade t, customer c, fabric f where t.c_number=c.c_number and t.f_number=f.f_number;

select t_number, f.f_number, c.c_number, c_name, t_amount, t_price, t_deposit, t_penalty, t_balance, t_receipt, t_unpaid, t_status, c_phone, c_address, t_registdate,  t_remarks, f_sort, f_name, f_color, f_size, f_weight, f_price from trade t, customer c, fabric f where t.c_number=c.c_number and t.f_number=f.f_number;

insert o_number,o.a_number,f_number, c.c_number, c_name,c_phone, o_email, o_address, o_amount, o_total, o_status,o_registdate,o_remarks from order1 o, account a, customer c where o.a_number=a.a_number;


select o_number, o.a_number,f.f_number, f_name, o_amount, o_total, c_name, c_phone, o_status, o_registdate, a_email, o_address, o_remarks from order1 o, fabric f, account a, customer c where o.a_number=a.a_number;


select o_number, o.a_number, f.f_number, c.c_number, o_email, o_address, o_amount, o_total, o_status, o_registdate, o_remarks, f_name,  c_name, c_phone from order1 o, fabric f, account a, customer c where o.f_number=f.f_number and o.a_number=a.a_number and o.c_number=c.c_number;

select o_number, a_number, f.f_number, f_name, o_amount, o_total, c_name, c_phone,  o_status, o_registdate, o_email, o_address,  o_remarks from order1 o, fabric f, customer c where o.f_number=f.f_number and o.c_number=c.c_number;

select o_number, a_number, f.f_number, f_name, o_amount, o_total, c_name, c_phone,  o_status, o_registdate, o_email, o_address,  o_remarks from order1 o, fabric f, customer c where o.f_number=f.f_number and o.c_number=c.c_number and c_name like '';
select o_number, o.a_number, f.f_number, c.c_number, o_email, o_address, o_amount, o_total, o_status, o_registdate, o_remarks, f_name,  c_name, c_phone from order1 o, fabric f, account a, customer c where o.f_number=f.f_number and o.a_number=a.a_number and o.c_number=c.c_number order by o_number a;



select o_number, o.a_number, f.f_number, c.c_number, o_email, o_address, o_amount, o_total, o_status, o_registdate, o_remarks, f_name,  c_name, c_phone from order1 o, fabric f, account a, customer c where o.f_number=f.f_number and o.a_number=a.a_number and o.c_number=c.c_number order by o_number;

