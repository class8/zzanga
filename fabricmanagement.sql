select *
from tab;

create table administrator(
id varchar2(20),
name varchar2(20) not null,
password varchar2(20) not null,
primary key(id)
);

insert into administrator(id,name,password)
VALUES ('admin','admin','admin');

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

create sequence account_seq
start with 1
increment by 1;

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
t_status varchar2(16) DEFAULT '?ôï?ù∏Ï§?Îπ?',
t_registdate date DEFAULT sysdate not null,
t_address varchar2(200) not null,
t_remarks varchar2(100),
primary key(t_number),
foreign key(f_number) references fabric(f_number),
foreign key(c_number) references customer(c_number)
);

create sequence trade_seq
start with 1
increment by 1;

create table order1(
o_number number,    
a_number number not null,
f_number varchar2(20) not null,
c_number number not null,
o_email varchar2(100),
o_address varchar2(200) not null,
o_amount number(10) DEFAULT 0 not null,
o_total number(20) DEFAULT 0 not null,
o_status varchar2(20) DEFAULT 'Ï£ºÎ¨∏?ãú?ûë' not null,
o_registdate date DEFAULT sysdate not null,
o_remarks varchar2(100),
primary key(o_number),
foreign key(f_number) references fabric(f_number),
foreign key(c_number) references customer(c_number),
foreign key(a_number) references account(a_number)
);

create sequence order_seq
start with 1
increment by 1;
