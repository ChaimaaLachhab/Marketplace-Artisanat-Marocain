
-- Modification de la table cart
ALTER TABLE cart ADD COLUMN customer_id BIGINT;
ALTER TABLE product ADD COLUMN rating FLOAT NOT NULL;
    ALTER TABLE cart ADD CONSTRAINT UK_cart_customer_id UNIQUE (customer_id);
ALTER TABLE cart ADD CONSTRAINT FK_cart_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

-- Création de la table cart_item
CREATE TABLE cart_item (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           quantity INTEGER NOT NULL,
                           cart_id BIGINT,
                           product_id BIGINT,
                           PRIMARY KEY (id)
) ENGINE=InnoDB;

-- Modification de la table orders
ALTER TABLE orders MODIFY COLUMN order_date DATETIME(6);

-- Modification de la table review
ALTER TABLE review ADD COLUMN product_id BIGINT;
ALTER TABLE review ADD CONSTRAINT FK_review_product FOREIGN KEY (product_id) REFERENCES product (id);

-- Création de la table sub_order_item
CREATE TABLE sub_order_item (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                quantity INTEGER NOT NULL,
                                product_id BIGINT,
                                sub_order_id BIGINT,
                                PRIMARY KEY (id)
) ENGINE=InnoDB;

-- Ajout des contraintes de clé étrangère avec des noms logiques
ALTER TABLE cart_item ADD CONSTRAINT FK_cart_item_cart FOREIGN KEY (cart_id) REFERENCES cart (id);
ALTER TABLE cart_item ADD CONSTRAINT FK_cart_item_product FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE sub_order_item ADD CONSTRAINT FK_sub_order_item_product FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE sub_order_item ADD CONSTRAINT FK_sub_order_item_sub_order FOREIGN KEY (sub_order_id) REFERENCES sub_order (id);
ALTER TABLE user ADD CONSTRAINT FK_user_photo FOREIGN KEY (user_photo_id) REFERENCES media (id);