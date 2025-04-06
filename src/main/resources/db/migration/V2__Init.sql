CREATE SEQUENCE IF NOT EXISTS complaint_id_seq;

CREATE TABLE IF NOT EXISTS complaint (
    id          bigint primary key,
    product_id  bigint not null,
    claimant    varchar(255) not null,
    content     text,
    created_at  timestamp,
    country     varchar(2),
    counter     integer
);

ALTER TABLE complaint ADD CONSTRAINT complaint_product_claimant_key UNIQUE (product_id, claimant);
