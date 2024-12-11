CREATE TABLE IF NOT EXISTS Furniture (
    furnitureID INT NOT NULL PRIMARY KEY,
    furniturePrice INT NOT NULL,
    furnitureQuantity INT NOT NULL,
    furnitureName VARCHAR(50) NOT NULL,
    furnitureType VARCHAR(50),
    furnitureDescription VARCHAR(500),
    furnitureImage VARCHAR(500)
);

