
INSERT INTO tservice.users
(user_name,user_surname,user_email,user_password,user_role)
VALUES
('Vania', 'Zichenko', 'vania@gmail.com','12345', 'CLIENT'),
('Yura', 'Senin', 'yura@gmail.com','12345', 'CLIENT'),
('admin', 'admin', 'admin@gmail.com', '12345', 'ADMIN'),
('Vasia', 'Zaichenko', 'vasia@gmail.com', '12345', 'DRIVER'),
('Petro', 'Petrovich', 'petro@gmail.com', '12345', 'DRIVER'),
('Anton', 'Antonovich', 'anton@gmail.com','12345', 'CLIENT'),
('Victor', 'Vicrorovich', 'victor@gmail.com','12345', 'CLIENT'),
('Vlad', 'Vladislavovich', 'vlad@gmail.com', '12345', 'DRIVER');


INSERT INTO tservice.taxis
( taxi_number, taxi_type, taxi_driver, taxi_busy)
VALUES
( 'AA1111AA', 'Premium', 4 ,false),
( 'AA2222AA', 'Premium', 5 ,false),
( 'AA3333AA', 'Premium', 8 ,false);


INSERT INTO tservice.addresses
( address_street, address_house,address_x,address_y)
VALUES
('Pivnichna', '1', '134','100'),
('Pivnichna', '2', '137','111'),
('Pivnichna', '3', '135','146'),
('Pivnichna', '4', '124','163'),
('Pivnichna', '5', '163','156'),
('Pivnichna', '6', '142','150'),
('Pivnichna', '7', '187','181');

INSERT INTO tservice.orders
(order_cost,order_startPoint,order_endPoint,order_status,user_id,taxi_id)
VALUES
(1500, 1, 2, 'DONE', 2, 1),
(2500, 3, 2, 'DONE', 2, 1),
(3500, 4, 2, 'DONE', 1, 2),
(4500, 5, 2, 'DONE', 2, 3),
(5500, 5, 3, 'DONE', 2, 3),
(6500, 1, 3, 'DONE', 2, 2),
(7500, 2, 3, 'DONE', 1, 1);

INSERT INTO tservice.coupons
(coupon_discount,user_id,order_id)
VALUES
('0.5', 1, 1),
('0.5', 2, 2),
('0.5', 6, 3);