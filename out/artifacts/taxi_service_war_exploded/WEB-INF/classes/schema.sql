CREATE DATABASE tservice;

CREATE TABLE tservice.users (
  user_id INT NOT NULL AUTO_INCREMENT KEY,
  user_name varchar(45) DEFAULT NULL,
  user_surname varchar(45) NOT NULL,
  user_email varchar(45) NOT NULL,
  user_password varchar(45) NOT NULL,
  user_role varchar(35) NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tservice.addresses (
  address_id INT NOT NULL AUTO_INCREMENT KEY,
  address_street varchar(40) NOT NULL,
  address_house INT NOT NULL,
  address_x INT NOT NULL,
  address_y INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tservice.taxis (
  taxi_id INT NOT NULL AUTO_INCREMENT KEY,
  taxi_number varchar(45) NOT NULL,
  taxi_type varchar(45) NOT NULL,
  taxi_driver INT NOT NULL,
  taxi_busy tinyint(1) NOT NULL,
   FOREIGN KEY (taxi_driver) REFERENCES tservice.users(user_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tservice.orders (
  order_id INT NOT NULL AUTO_INCREMENT KEY,
  order_cost DOUBLE NOT NULL,
  order_startPoint INT NOT NULL,
  order_endPoint INT NOT NULL,
  order_status varchar(20) NOT NULL,
  user_id INT NOT NULL,
  taxi_id INT NOT NULL,

  FOREIGN KEY (order_startPoint) REFERENCES tservice.addresses(address_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (order_endPoint) REFERENCES tservice.addresses(address_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (user_id) REFERENCES tservice.users(user_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (taxi_id) REFERENCES tservice.taxis(taxi_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tservice.coupons (
  coupon_id INT NOT NULL AUTO_INCREMENT KEY,
  coupon_discount decimal(5,2) NOT NULL,
  user_id INT NOT NULL,
  order_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES tservice.users(user_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
     FOREIGN KEY (order_id) REFERENCES tservice.orders(order_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


