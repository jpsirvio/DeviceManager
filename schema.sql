SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS devices;
DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS categories;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE persons (
person_id BIGINT NOT NULL AUTO_INCREMENT
, username VARCHAR(20) NOT NULL
, email VARCHAR(100) NOT NULL
, first_name VARCHAR(100) NOT NULL
, last_name VARCHAR(100) NOT NULL
, notes VARCHAR(900) 
, password_hash VARCHAR(250)
, role VARCHAR(20) NOT NULL
, admin TINYINT NOT NULL
, deleted TINYINT NOT NULL
, PRIMARY KEY (person_id)
);

INSERT INTO persons (username, email, first_name, last_name, notes, password_hash, role, admin, deleted)
VALUES ("-----"," ","unspecified","-","This is a unspecified user for devices without a named user","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",0,0)
, ("11111","first.user@test.com","First","User","This is the first user. Admin, not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",1,0)
, ("22222","second.user@test.com","Second","User","Second user, not an admin, not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",0,0)
, ("user","user@test.com","User","User","Test User","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",0,0)
, ("admin","admin@test.com","Admin","Admin","Test Admin","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",1,0)
, ("dev","dev@dev.com","Dev","Dev","Developer","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",1,0);

CREATE TABLE locations (
location_id BIGINT NOT NULL AUTO_INCREMENT
, office VARCHAR(100)
, address VARCHAR(100)
, department VARCHAR(100)
, unit VARCHAR(100)
, room VARCHAR(100)
, notes VARCHAR(900)
, deleted TINYINT NOT NULL
, PRIMARY KEY (location_id)
);

INSERT INTO locations (office, address, department, unit, room, notes, deleted)
VALUES ("Test Office","1 Office Street, Testville","Department A","Unit 1","2nd Floor, Room 3","This is a note",0)
, ("Old Office","4 Office Street, Testville","Department B","Unit 3","5th Floor, Room 6","This is a note",0)
, ("w/ user","","","","","This is for devices carried by the user and with no specific location",0);

CREATE TABLE categories (
category_id BIGINT NOT NULL AUTO_INCREMENT
, cname VARCHAR(50)
, notes VARCHAR(900)
, deleted TINYINT NOT NULL
, PRIMARY KEY (category_id)
);

INSERT INTO categories (cname, notes, deleted)
VALUES ("Laptop","This category is for laptop computers", 0)
, ("Smart Phone","This category is for smart phone", 0)
, ("Deleted Category","This category is deleted", 1)
, ("Video Conferencing Device","This category is for video conferencing devices", 0);

CREATE TABLE devices
(device_id BIGINT NOT NULL AUTO_INCREMENT
, product VARCHAR(100) 
, model VARCHAR(100) 
, serialnumber VARCHAR(100) 
, notes VARCHAR(900) 
, deleted TINYINT
, date_created DATE
, date_edited DATE
, date_deleted DATE
, person_id BIGINT
, location_id BIGINT
, category_id BIGINT
, PRIMARY KEY (device_id)
, FOREIGN KEY (person_id) REFERENCES persons(person_id)
, FOREIGN KEY (location_id) REFERENCES locations(location_id)
, FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

INSERT INTO devices (person_id, location_id, category_id, product, model, serialnumber, notes, deleted)
VALUES (1, 1, 1, "demo laptop", "Dell Latitude 5410", "ABC1234", "This is a note for a demo laptop", 0)
, (2, 3, 1, "demo laptop", "Dell Latitude 5410", "BCD2345", "This is a note for a demo laptop", 0)
, (3, 3, 1, "demo laptop", "Dell Latitude 5410", "CDE3456", "This is a note for a demo laptop", 0)
, (1, 1, 2, "demo smartphone", "Samsung A52", "123456789", "This is a note for a demo smart phone", 0)
, (2, 3, 2, "demo smartphone", "Samsung A52", "234567891", "This is a note for a demo smart phone", 0)
, (3, 3, 2, "demo smartphone", "Samsung A52", "345678912", "This is a note for a demo smart phone", 0);

SELECT * FROM devices;
SELECT * FROM persons;
SELECT * FROM locations;
SELECT * FROM categories;