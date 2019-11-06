INSERT INTO taxi_service.taxis
(id,location_street,location_house,driver_name,car_number,busy,car_type)
VALUES
('1', 'A', '1', 'Vania', 'AA1111AA', '1', 'Premium'),
('2', 'B', '1', 'Vacyl', 'AA2222AA', '1', 'Premium');

INSERT INTO taxi_service.users
(id,name,surname,email,password,money_spent,role)
VALUES
('1', 'servlet', 'servlet', 'servlet@gmail.com', 'servlet','12345', 'Client'),
('2', 'admin', 'admin', 'adnib@gmail.com','admin', '12345', 'Admin');


INSERT INTO taxi_service.coupons
(id,discount_percent,id_order,id_user)
VALUES
('1', '0.5', NULL, '1');