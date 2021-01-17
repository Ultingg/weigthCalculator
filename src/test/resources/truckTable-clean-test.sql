DROP TABLE IF EXISTS truck;
CREATE TABLE IF NOT EXISTS truck (
                                     id BIGINT AUTO_INCREMENT NOT NULL ,
                                     truck_number VARCHAR(255) NOT NULL UNIQUE ,
                                     front_price DOUBLE not null ,
                                     rear_price DOUBLE NOT NULL ,
                                     first_wheel_weight DOUBLE NOT NULL ,
                                     truck_weight DOUBLE not null ,
                                     PRIMARY KEY (id)
);