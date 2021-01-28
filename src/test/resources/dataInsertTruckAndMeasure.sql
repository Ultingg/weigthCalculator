INSERT INTO truck (id, first_wheel_weight, front_price, rear_price, truck_number, truck_weight)
VALUES (1, 6000, 400, 600, 'EGW-542/DNM-698', 16500),
       (2, 6100, 380, 650.0, 'ROQ-675/DNO-777', 16800),
       (3, 5400, 350, 700.0, 'LRA-547/MOT-193', 16300),
       (4, 5800, 400, 500, 'FKX-875/DKL-686', 15800);
INSERT INTO measure (id, cargo_weight, complete_weight, date_of_measure, front_bar, front_weight, rear_bar, rear_weight,
                     overloaded, truck_id, front_overloaded, rear_overloaded, complete_overloaded)
VALUES (1, 25000, 41500, '2020-01-01 08:00:00', 5.2, 14800, 3.45, 20700, false, 1, false, false, false),
       (2, 27100, 43500, '2020-01-03 08:00:00', 5.2, 14800, 3.8, 22800, false, 1, false, false, false),
       (3, 27100, 41600, '2020-01-04 08:00:00', 5.5, 16000, 3.6, 21600, true, 1, true, false, false),
       (4, 29000, 45500, '2020-01-05 08:00:00', 5.5, 16000, 4, 24000, true, 1, true, true, true);
