CREATE TABLE users (
  id int(19) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  surname varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  money_spent varchar(45) DEFAULT NULL,
   role varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY email_UNIQUE (email)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE taxis (
  id int(19) NOT NULL AUTO_INCREMENT,
   location_street varchar(45) DEFAULT NULL,
  location_house int(11) DEFAULT NULL,
  driver_name varchar(45) NOT NULL,
  car_number varchar(45) NOT NULL,
  busy tinyint(1) DEFAULT NULL,
  car_type varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  UNIQUE KEY car_number_UNIQUE (car_number)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE orders (
  id int(19) NOT NULL AUTO_INCREMENT,
  cost varchar(45) DEFAULT NULL,
  start_street varchar(45) DEFAULT NULL,
  start_house int(11) DEFAULT NULL,
  end_street varchar(45) DEFAULT NULL,
  end_house int(11) DEFAULT NULL,
  distance int(11) DEFAULT NULL,
  waiting_time varchar(45) DEFAULT NULL,
  driving_time varchar(45) DEFAULT NULL,
  order_date datetime DEFAULT NULL,
  car_type varchar(45) DEFAULT NULL,
  status varchar(45) DEFAULT NULL,
  id_user int(11) NOT NULL,
  id_taxi int(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  KEY fk_orders_users1_idx (id_user),
  KEY fk_orders_taxis1_idx (id_taxi),
  CONSTRAINT fk_orders_taxis1 FOREIGN KEY (id_taxi) REFERENCES taxis (id),
  CONSTRAINT fk_orders_users1 FOREIGN KEY (id_user) REFERENCES users (id)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE coupons (
  id int(19) NOT NULL AUTO_INCREMENT,
  id_user int(11) NOT NULL,
  id_order int(11) DEFAULT NULL,
  discount_percent decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_coupon_users1_idx (id_user),
  KEY fk_coupons_orders1_idx (id_order),
  CONSTRAINT fk_coupon_users1 FOREIGN KEY (id_user) REFERENCES users (id),
  CONSTRAINT fk_coupons_orders1 FOREIGN KEY (id_order) REFERENCES orders (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;