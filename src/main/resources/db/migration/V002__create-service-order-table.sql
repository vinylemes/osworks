CREATE TABLE IF NOT EXISTS service_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    costumer_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    opening_date DATETIME NOT NULL,
    closing_date DATETIME,

    PRIMARY KEY (id)
);

ALTER TABLE service_order ADD CONSTRAINT fk_service_order_costumer
FOREIGN KEY (costumer_id) REFERENCES costumer (id);