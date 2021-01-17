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