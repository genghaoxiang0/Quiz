DROP DATABASE IF EXISTS quiz;
CREATE DATABASE quiz;

create table user (
    id int primary key not null auto_increment,
    username varchar(20),
    password varchar(20),
    name varchar(20),
    email varchar(30),
    is_admin boolean default false,
    active boolean default true
) engine = innodb;

insert into user (username, password, name, email, is_admin) values ('admin', 'admin', 'Admin User', 'admin@quiz.com', true);
insert into user (username, password, name, email) values ('genghaoxiang', '111111', 'Haoxiang Geng', 'hgeng2@andrew.cmu.edu');

create table category (
    name varchar(20) primary key not null
) engine = innodb;

insert into category (name) values ('Math');
insert into category (name) values ('Java');
insert into category (name) values ('English');

create table choice_question (
    id int primary key not null auto_increment,
    category varchar(20),
    description varchar(400),
    choice_a varchar(200),
    choice_b varchar(200),
    choice_c varchar(200),
    choice_d varchar(200),
    answer varchar(10) check ( answer in ('A','B','C','D') ),
    foreign key (category) references category(name)
) engine = innodb;

create table quiz (
    id int primary key not null auto_increment,
    name varchar(50),
    category varchar(20),
    user_id int not null,
    start_time datetime,
    finish_time datetime,
    score int,
    foreign key (category) references category(name),
    foreign key (user_id) references user(id)
) engine = innodb;

create table quiz_choice_question (
    id int primary key not null auto_increment,
    quiz_id int not null,
    choice_question_id int not null,
    answer varchar(10),
    foreign key (quiz_id) references quiz(id),
    foreign key (choice_question_id) references choice_question(id)
) engine = innodb;

create table feedback (
    id int primary key not null auto_increment,
    user_id int,
    rate int,
    comment varchar(500),
    foreign key (user_id) references user(id)
) engine = innodb;

insert into feedback (user_id, rate, comment) VALUES (1, 5, 'Good');

create table message (
    id int primary key not null auto_increment,
    user_id int,
    title varchar(100),
    content varchar(500),
    time datetime,
    unread boolean default true,
    foreign key (user_id) references user(id)
) engine = innodb;