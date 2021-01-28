CREATE USER IF NOT EXISTS sa PASSWORD '';
ALTER USER sa ADMIN true;
CREATE SCHEMA IF NOT EXISTS test AUTHORIZATION sa;
DROP TABLE IF EXISTS truck;
CREATE TABLE truck
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    truck_number       VARCHAR(255)          NOT NULL UNIQUE,
    front_price        DOUBLE                not null,
    rear_price         DOUBLE                NOT NULL,
    first_wheel_weight DOUBLE                NOT NULL,
    truck_weight       DOUBLE                not null,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS measure;
CREATE TABLE measure
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    cargo_weight        double,
    truck_id            INT                   NOT NULL,
    complete_weight     double,
    date_of_measure     timestamp(6),
    front_bar           double,
    front_weight        double,
    rear_bar            double,
    rear_weight         double,
    overloaded          bit(1),
    front_overloaded    bit(1),
    rear_overloaded     bit(1),
    complete_overloaded bit(1)
);