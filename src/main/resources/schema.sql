create TABLE media (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     type varchar(50) NOT NULL,
     name varchar(50)  NOT NULL,
     author varchar(50)  NOT NULL,
     themes varchar(50)  NOT NULL,
     publishing_house varchar(50)  NOT NULL,
     print_date timestamp  NOT NULL,
     identifier varchar(50) unique NOT NULL
);