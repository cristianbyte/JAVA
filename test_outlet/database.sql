CREATE DATABASE test_db_java;
USE test_db_java;

CREATE TABLE stores (
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
    );

CREATE TABLE customers (
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
    );
    
CREATE TABLE products (
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL NOT NULL,
    id_store INT NOT NULL,
    FOREIGN KEY (id_store) REFERENCES stores(id) ON DELETE CASCADE
    );
    
CREATE TABLE purchases (
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    purchase_date DATE NOT NULL,
    quantity INT NOT NULL,
    id_customer INT NOT NULL,
    id_product INT NOT NULL,
    FOREIGN KEY (id_customer) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (id_product) REFERENCES products(id) ON DELETE CASCADE
);

ALTER TABLE products add stock INT NOT NULL;

# data stores
INSERT INTO stores (name,location) VALUES("dollarcity","store: 224");
INSERT INTO stores (name,location) VALUES("Lilipink","store: 106");
INSERT INTO stores (name,location) VALUES("Juan Valdez","bubble: B146");
INSERT INTO stores (name,location) VALUES("Tostao","store: 150");
INSERT INTO stores (name,location) VALUES("Mimos","store: 136");

#data products
INSERT INTO products (name,price,id_store,stock) VALUES("Gansito",4900,1,20);
INSERT INTO products (name,price,id_store,stock) VALUES("Galeltas Festivalx6",6900,1,12);
INSERT INTO products (name,price,id_store,stock) VALUES("Jugo HIT",2500,1,10);
INSERT INTO products (name,price,id_store,stock) VALUES("Jean Aweasome",95000,2,10);
INSERT INTO products (name,price,id_store,stock) VALUES("Shirt Aweasome",95000,2,10);

#data customers 
INSERT INTO customers (name,last_name,email) VALUES("Peter Benjamin", "Parker","peter@gmail.com");
INSERT INTO customers (name,last_name,email) VALUES("Clark", "Kent","clark@gmail.com");
INSERT INTO customers (name,last_name,email) VALUES("Elon", "Musk","elon@x.com");


# testing
SELECT * FROM products WHERE products.name LIKE "gan%" ORDER BY products.id ASC;

