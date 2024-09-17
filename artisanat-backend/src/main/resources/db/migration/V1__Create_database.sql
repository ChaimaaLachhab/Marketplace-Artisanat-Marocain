-- Script Flyway pour créer la base de données avec les clés étrangères après la création des tables

-- Création de la table user
CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    email VARCHAR(255),
                                    full_name VARCHAR(255),
                                    password VARCHAR(255),
                                    phone VARCHAR(255),
                                    role ENUM('ADMIN', 'ARTISAN', 'CUSTOMER'),
                                    username VARCHAR(255),
                                    user_photo_id BIGINT UNIQUE,
                                    PRIMARY KEY (id)
);

-- Création de la table admin
CREATE TABLE IF NOT EXISTS admin (
                                     id BIGINT NOT NULL,
                                     PRIMARY KEY (id)
);

-- Création de la table artisan
CREATE TABLE IF NOT EXISTS artisan (
                                       experience INTEGER NOT NULL,
                                       location VARCHAR(255),
                                       specialty ENUM('CARPET_MAKING', 'EMBROIDERY', 'FURNITURE_MAKING', 'GLASSBLOWING', 'JEWELRY_MAKING', 'LEATHERWORK', 'MARQUETRY', 'METALWORK', 'MOSAIC_MAKING', 'POTTERY', 'SHOE_MAKING', 'STONEMASONRY', 'TAPESTRY', 'WEAVING', 'WOOD_CARVING'),
                                       verification_status ENUM('APPROVED', 'PENDING', 'REJECTED'),
                                       id BIGINT NOT NULL,
                                       PRIMARY KEY (id)
);

-- Création de la table customer
CREATE TABLE IF NOT EXISTS customer (
                                        id BIGINT NOT NULL,
                                        cart_id BIGINT UNIQUE,
                                        loyalty_id BIGINT UNIQUE,
                                        PRIMARY KEY (id)
);

-- Création de la table cart
CREATE TABLE IF NOT EXISTS cart (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    PRIMARY KEY (id)
);

-- Création de la table loyalty
CREATE TABLE IF NOT EXISTS loyalty (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       points INTEGER NOT NULL,
                                       PRIMARY KEY (id)
);

-- Création de la table product
CREATE TABLE IF NOT EXISTS product (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       category ENUM('BAGS', 'BEDS', 'BELTS', 'BOWLS', 'CABINETS', 'CANDLES', 'CHAIRS', 'CUTLERY', 'DRESSES', 'GLASSWARE', 'HATS', 'JACKETS', 'JEWELRY', 'KAFTANS', 'LANTERNS', 'PANTS', 'PLATES', 'RUGS', 'SCARVES', 'SHIRTS', 'SOFAS', 'TABLES', 'TEAPOTS', 'VASES', 'WALL_ART'),
                                       collection ENUM('ACCESSORIES', 'CLOTHING', 'FURNITURE', 'HOME_DECOR', 'KITCHENWARE'),
                                       created_at DATETIME(6),
                                       description VARCHAR(255),
                                       name VARCHAR(255),
                                       price FLOAT(23) NOT NULL,
                                       stock INTEGER NOT NULL,
                                       artisan_id BIGINT,
                                       cart_id BIGINT,
                                       PRIMARY KEY (id)
);

-- Création de la table media
CREATE TABLE IF NOT EXISTS media (
                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                     media_id VARCHAR(255),
                                     media_url VARCHAR(255),
                                     type TINYINT CHECK (type BETWEEN 0 AND 1),
                                     product_id BIGINT,
                                     user_id BIGINT UNIQUE,
                                     PRIMARY KEY (id)
);

-- Création de la table orders
CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      location VARCHAR(255),
                                      order_date VARCHAR(255),
                                      quantity INTEGER NOT NULL,
                                      status ENUM('CANCELLED', 'DELIVERED', 'PENDING', 'RETURNED', 'SHIPPED'),
                                      total_amount FLOAT(53) NOT NULL,
                                      customer_id BIGINT,
                                      PRIMARY KEY (id)
);

-- Création de la table order_product
CREATE TABLE IF NOT EXISTS order_product (
                                             product_id BIGINT NOT NULL,
                                             order_id BIGINT NOT NULL
);

-- Création de la table product_media
CREATE TABLE IF NOT EXISTS product_media (
                                             product_id BIGINT NOT NULL,
                                             media_id BIGINT NOT NULL
);

-- Création de la table review
CREATE TABLE IF NOT EXISTS review (
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      comment VARCHAR(255),
                                      rating INTEGER NOT NULL,
                                      review_date DATETIME(6),
                                      customer_id BIGINT,
                                      PRIMARY KEY (id)
);

-- Création de la table product_reviews
CREATE TABLE IF NOT EXISTS product_reviews (
                                               product_id BIGINT NOT NULL,
                                               reviews_id BIGINT NOT NULL
);

-- Création de la table sub_order
CREATE TABLE IF NOT EXISTS sub_order (
                                         id BIGINT NOT NULL AUTO_INCREMENT,
                                         sub_total FLOAT(53) NOT NULL,
                                         artisan_id BIGINT,
                                         order_id BIGINT,
                                         PRIMARY KEY (id)
);

-- Création de la table sub_order_products
CREATE TABLE IF NOT EXISTS sub_order_products (
                                                  sub_order_id BIGINT NOT NULL,
                                                  product_id BIGINT NOT NULL
);

-- Ajout des clés étrangères après création des tables

-- Foreign key pour admin
ALTER TABLE admin
    ADD CONSTRAINT fk_admin_user FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE;

-- Foreign key pour artisan
ALTER TABLE artisan
    ADD CONSTRAINT fk_artisan_user FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE;

-- Foreign key pour customer
ALTER TABLE customer
    ADD CONSTRAINT fk_customer_cart FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_customer_loyalty FOREIGN KEY (loyalty_id) REFERENCES loyalty (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_customer_user FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE;

-- Foreign key pour product
ALTER TABLE product
    ADD CONSTRAINT fk_product_artisan FOREIGN KEY (artisan_id) REFERENCES artisan (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_product_cart FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE;

-- Foreign key pour media
ALTER TABLE media
    ADD CONSTRAINT fk_media_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_media_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE;

-- Foreign key pour orders
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE;

-- Foreign key pour order_product
ALTER TABLE order_product
    ADD CONSTRAINT fk_order_product_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_order_product_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE;

-- Foreign key pour product_media
ALTER TABLE product_media
    ADD CONSTRAINT fk_product_media_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_product_media_media FOREIGN KEY (media_id) REFERENCES media (id) ON DELETE CASCADE;

-- Foreign key pour review
ALTER TABLE review
    ADD CONSTRAINT fk_review_customer FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE;

-- Foreign key pour product_reviews
ALTER TABLE product_reviews
    ADD CONSTRAINT fk_product_reviews_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_product_reviews_review FOREIGN KEY (reviews_id) REFERENCES review (id) ON DELETE CASCADE;

-- Foreign key pour sub_order
ALTER TABLE sub_order
    ADD CONSTRAINT fk_sub_order_artisan FOREIGN KEY (artisan_id) REFERENCES artisan (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_sub_order_orders FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE;

-- Foreign key pour sub_order_products
ALTER TABLE sub_order_products
    ADD CONSTRAINT fk_sub_order_products_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_sub_order_products_sub_order FOREIGN KEY (sub_order_id) REFERENCES sub_order (id) ON DELETE CASCADE;
