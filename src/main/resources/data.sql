INSERT INTO truck (id, first_wheel_weight, front_price, rear_price, truck_number, truck_weight)
VALUES (1, 6000, 385, 495, 'TYV-855/DKN-585', 16510),
       (2, 5100, 400, 710.0, 'MOY-909/DKN-586', 16800),
       (4, 7400, 500, 650.0, 'FKX-532/DKN-584', 15830),
       (6, 6000, 395, 510, 'WRT-457/WZA-213', 16510),
       (30, 7000, 354, 700, 'ROT-943/DKE-757', 16855),
       (34, 6000, 510, 650, 'A769YM47/AH271447', 18000);



INSERT INTO measure (id, cargo_weight, complete_weight, date_of_measure, front_bar, front_weight, rear_bar, rear_weight,
                     overloaded, truck_id, front_overloaded, rear_overloaded, complete_overloaded)
VALUES (1, 25000, 41500, '2020-01-01 08:00:00', 5.2, 14800, 3.45, 20700, false, 1, false, false, false),
       (2, 27100, 43500, '2020-01-03 08:00:00', 5.2, 14800, 3.8, 22800, false, 1, false, false, false),
       (3, 27100, 41600, '2020-01-04 08:00:00', 5.5, 16000, 3.6, 21600, true, 1, true, false, false),
       (4, 29000, 45500, '2020-01-05 08:00:00', 5.5, 16000, 4, 24000, true, 1, true, true, true),
       (5, 33870, 50400, '2021-01-20 05:50:22', 5.5, 16900, 4, 28400, true, 2, true, true, true),
       (6, 25880, 42410, '2021-01-21 07:50:00', 5.1, 15300, 3.1, 22010, false, 2, false, false, false),
       (7, 32270, 48100, '2021-01-22 12:00:00', 5.2, 18600, 3.4, 22100, true, 4, true, false, true),
       (8, 31620, 47450, '2021-01-23 08:00:00', 5.2, 18600, 3.3, 21450, true, 4, true, false, true),
       (9, 33500, 51500, '2021-01-23 12:00:00', 5, 19500, 4, 26000, true, 34, true, true, true),
       (10, 25800, 43800, '2021-01-24 08:00:00', 4, 14400, 3.6, 23400, true, 1, false, true, false),
       (11, 22545, 39400, '2021-01-24 14:00:00', 5, 10700, 3.1, 21700, false, 30, false, false, false),
       (12, 25615, 42125, '2021-01-24 18:00:00', 5.5, 15725, 4, 20400, true, 6, true, false, false);
