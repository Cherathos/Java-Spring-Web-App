CREATE TABLE IF NOT EXISTS Furniture (
    id INT NOT NULL PRIMARY KEY,
    price INT NOT NULL,
    quantity INT NOT NULL,
    furniturename VARCHAR(50) NOT NULL,
    furnituretype VARCHAR(50),
    description VARCHAR(500),
    image VARCHAR(500),
    version INT
);

