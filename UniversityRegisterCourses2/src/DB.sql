create table managerjoin(
 id varchar2(20) not null,
 password varchar2(10) not null,
 name varchar2(20) not null,
 primary key(id)
);


create table subject(
 no number not null,
 s_num varchar2(2) not null,
 s_name varchar2(24) not null,
 primary key(no),
 unique(s_num)
);

create sequence subject_seq
start with 1 
increment by 1;


drop table subject;
drop sequence subject_seq;


create table student(
no number not null,
sd_num varchar2(8) not null,
sd_name varchar2(12) not null,
sd_id varchar2(12) not null,
sd_passwd varchar2(12) not null,
s_num varchar2(2) not null,
sd_birthday varchar2(8) not null,
sd_phone varchar2(15) not null,
sd_address varchar2(80) not null,
sd_email varchar2(40) not null,
sd_date date default sysdate,
primary key(no),
unique(sd_num),
unique(sd_id),
foreign key(s_num) references subject(s_num)
);

create sequence student_seq
start with 1 
increment by 1;


drop table student;
drop sequence student_seq;


select st.no as no, sd_num, sd_name, sd_id, sd_passwd, su.s_name as s_name, sd_birthday, sd_phone, sd_address, sd_email, sd_date 
from STUDENT st, SUBJECT su 
where st.s_num = su.s_num
order by no;


create table lesson(
 no number not null,
 l_num varchar2(2) not null,
 l_name varchar2(20) not null,
 primary key(no),
 unique(l_num)
);

create sequence lesson_seq
start with 1 
increment by 1;


drop table lesson;
drop sequence lesson_seq;


create table trainee(
 no number not null,
 sd_num varchar2(8) not null,
 l_num varchar2(2) not null,
 t_section varchar2(20) not null,
 t_date date default sysdate,
 primary key(no),
 foreign key(sd_num) references student(sd_num),
 foreign key(l_num) references lesson(l_num)
);

create sequence trainee_seq
start with 1 
increment by 1;


drop table trainee;
drop sequence trainee_seq;
