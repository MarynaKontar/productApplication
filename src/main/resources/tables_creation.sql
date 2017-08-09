CREATE TABLE IF NOT EXISTS products (
  id          INTEGER GENERATED BY DEFAULT AS IDENTITY,
  name        VARCHAR(405) DEFAULT NULL,
  cost        DECIMAL DEFAULT NULL,
  finalStorageDate TIMESTAMP
  manufacturer_name VARCHAR(405) DEFAULT NULL,
  manufacturer_adress VARCHAR(405) DEFAULT NULL
  manufacturer_phoneNumber VARCHAR(405) DEFAULT NULL
  PRIMARY KEY (id)
);
