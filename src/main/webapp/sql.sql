create table user(
id varchar(20), 
user_pass varchar(20), 
user_name varchar(20), 
user_zipcode varchar(20), 
user_addr varchar(50), 
user_addr2 varchar(50), 
user_phone varchar(30),
user_bye boolean,
primary key(id)
);

create table buy(
buy_num int AUTO_INCREMENT, 
id varchar(20), 
buy_date date, 
buy_memo varchar(100),
buy_state varchar(20), 
buy_totalmoney int, 
primary key(buy_num)
);

create table qna(
qna_num int AUTO_INCREMENT, 
id varchar(20), 
qna_subject varchar(100), 
qna_content varchar(200), 
qna_date date, 
qna_answer boolean, 
qna_reply varchar(200), 
primary key(qna_num)
);

create table delivery(
deli_num int AUTO_INCREMENT,
id varchar(20),
deli_name varchar(20), 
deli_zipcode int, 
deli_addr varchar(50), 
deli_addr2 varchar(50),
deli_username varchar(20), 
deli_phone int, 
primary key(deli_num)
);

create table cart(
cart_num int AUTO_INCREMENT,
p_num int,
id varchar(20),
cart_qty int,
primary key(cart_num)
);

drop table order1;
