CREATE TABLE users(
username VARCHAR(35) PRIMARY KEY,
password VARCHAR(35) NOT NULL,
isAdmin BOOLEAN NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(20) NOT NULL,
phone_number VARCHAR(20) NOT NULL UNIQUE,
email_adress VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE hotel(
hotel_id CHAR(5) PRIMARY KEY,
number_of_rooms INTEGER NOT NULL,
rating INTEGER NOT NULL,
name VARCHAR(30) NOT NULL,
location VARCHAR(30) NOT NULL
);

CREATE TABLE room (
room_id CHAR(5) PRIMARY KEY,
room_number INTEGER NOT NULL,
room_type VARCHAR(20) NOT NULL,
room_floor INTEGER NOT NULL,
room_price INTEGER NOT NULL,
hotel_id CHAR(5) NOT NULL,
CONSTRAINT fk_hotel_id
FOREIGN KEY (hotel_id)
REFERENCES hotel (hotel_id)
);

CREATE TABLE reservation (
reservation_id CHAR(5) PRIMARY KEY,
username VARCHAR(35) NOT NULL UNIQUE,
hotel_id CHAR(5) NOT NULL,
room_id CHAR(5) NOT NULL UNIQUE,
reservation_status VARCHAR(20) NOT NULL,
check_in_date DATE NOT NULL,
check_out_date DATE NOT NULL,
CONSTRAINT fk_username
FOREIGN KEY (username)
REFERENCES users(username),
CONSTRAINT fk_hotel_id
FOREIGN KEY (hotel_id)
REFERENCES hotel(hotel_id),
CONSTRAINT fk_room_id
FOREIGN KEY (room_id)
REFERENCES room(room_id)
);

CREATE TABLE payment (
payment_id CHAR(5) PRIMARY KEY,
username VARCHAR(35) NOT NULL UNIQUE,
reservation_id CHAR(5) NOT NULL UNIQUE,
room_price INTEGER NOT NULL,
number_of_nights INTEGER NOT NULL,
total INTEGER NOT NULL,
payment_status VARCHAR(20),
CONSTRAINT fk_username
FOREIGN KEY(username)
REFERENCES users(username),
CONSTRAINT fk_reservation_id
FOREIGN KEY(reservation_id)
REFERENCES reservation(reservation_id)
);

ALTER TABLE room ADD COLUMN is_available boolean DEFAULT true;

ALTER TABLE payment ALTER column room_price TYPE varchar(20)
ALTER TABLE payment ALTER column number_of_nights TYPE varchar(5)
ALTER TABLE payment ALTER column total TYPE varchar(20)


-------------------------------------------------------------------------------------------------------------
INSERT INTO users VALUES ('AurelianT', '4420', FALSE, 'Aurelian', 'Temisan', '0744896214', 'a.temisan@yahoo.com');
INSERT INTO users VALUES ('LutuC', 'portofel', TRUE, 'Lutu', 'Camataru', '0766852102', 'camataru.lutu@gmail.com');
INSERT INTO users VALUES ('AlexandruR', 'med123', FALSE, 'Alexandru', 'Rafila', '0768521478', 'rafirafi@gmail.com');
INSERT INTO users VALUES ('VasilcoiuI', 'pas000', FALSE, 'Ili Marian', 'Vasilcoiu', '0743159753', 'iliemarian@yahoo.com');
INSERT INTO users VALUES ('VasileP', 'parola321', FALSE, 'Vasile', 'Prodan', '0748891114', 'v.prodan@yahoo.com');
INSERT INTO users VALUES ('SileC', 'buzunar', TRUE, 'Sile', 'Camataru', '0722302102', 'camataru.sile@gmail.com');
INSERT INTO users VALUES ('BogdanS', '5544', TRUE, 'Bogdan', 'Serban', '0765369742', 'bogdanserb@gmail.com');
INSERT INTO users VALUES ('MarianS', 'parola135', FALSE, 'Marian', 'Stoica', '0754895256', 'stoicamarian@yahoo.com');
INSERT INTO users VALUES ('PaulD', 'parola987', FALSE, 'Paul', 'Dondoe', '0743996214', 'd.paul@yahoo.com');
INSERT INTO users VALUES ('ConstantinM', 'parola357', FALSE, 'Constantin', 'Mogosanu', '0755685492', 'mogos.ctin@gmail.com');
INSERT INTO users VALUES ('CosminV', 'pas557', FALSE, 'Cosmin', 'Vlad', '0769621478', 'cosminv@gmail.com');
INSERT INTO users VALUES ('IonT', 'ambani123', FALSE, 'Ion Ion', 'Tiriac', '0744589321', 'ionion@yahoo.com');
INSERT INTO users VALUES ('DanielaM', 'parola753', TRUE, 'Daniela', 'Marcu', '0749895514', 'm.daniela@yahoo.com');
INSERT INTO users VALUES ('AlexandruM', 'parola963', FALSE, 'Alexandra', 'Marinescu', '0766001202', 'marinescu.alexandra@gmail.com');
INSERT INTO users VALUES ('CristinaU', 'parola9876', FALSE, 'Cristina Teodora', 'Uncheasu', '0768961002', 'cris.uncheasu@gmail.com');
INSERT INTO users VALUES ('MonicaT', 'parola1415', FALSE, 'Monica Daniela', 'Tunaru', '0763012021', 'tunaru.dani@yahoo.com');
INSERT INTO users VALUES ('AuraC', 'parola1213', FALSE, 'Aura', 'Calota', '0766895147', 'a.calota@yahoo.com');
INSERT INTO users VALUES ('AlexandruN', 'parola9101', FALSE, 'Alexandru', 'Naicu', '0766850007', 'rosu.alex@gmail.com');
INSERT INTO users VALUES ('MonicaB', 'monmon', TRUE, 'Monica', 'Barladeanu', '0765789456', 'moni.moni@gmail.com');
INSERT INTO users VALUES ('DanielN', 'pas415', FALSE, 'Daniel', 'Naicu', '0744558812', 'naicu.daniel@yahoo.com');
INSERT INTO users VALUES ('AdrianN', 'glontul123', TRUE, 'Adrian', 'Nastase', '0744001122', 'cris.maria@yahoo.com');
INSERT INTO users VALUES ('CrinaP', 'pas6615', FALSE, 'Crina', 'Popa', '0785274102', 'popa.cristina@gmail.com');
INSERT INTO users VALUES ('MarianG', 'militiamea', TRUE, 'Marian', 'Godina', '0773578108', 'zamfir.bogdan@gmail.com');
INSERT INTO users VALUES ('GabrielV', 'parola1617', FALSE, 'Gabriela Alexandra', 'Vasilcoiu', '0743397158', 'gabi.vasilcoiu@yahoo.com');
INSERT INTO users VALUES ('GhitaM', 'minge123', FALSE, 'Ghita', 'Muresan', '0744920783', 'maria.ispas@yahoo.com');
INSERT INTO users VALUES ('IonM', 'parola1819', FALSE, 'Ion Marin', 'Ciubotariu', '0755248963', 'ciubotariu.marin@gmail.com');
INSERT INTO users VALUES ('RobertS', '559988', FALSE, 'Robert', 'Sperila', '0743258147', 'sperila.robert@gmail.com');
INSERT INTO users VALUES ('MadalinT', 'pass3322', FALSE, 'Madalin', 'Toma', '0758951357', 'toma.madalin@yahoo.com');
INSERT INTO users VALUES ('AurelianC', 'pas1009', FALSE, 'Aurelian Ionut', 'Cristoiu', '0744998855', 'cristoiu.aurelian@yahoo.com');
INSERT INTO users VALUES ('ClaraS', 'fclar123', FALSE, 'Clara Maria', 'Serban', '0754892301', 'serban.clara@gmail.com');
INSERT INTO users VALUES ('TatianaF', 'pas55990', FALSE, 'Tatiana Floriana', 'Belulescu', '0756849210', 'tatiana.floriana@gmail.com');
------------------------------------------------------------------------------------------------------------
INSERT INTO hotel VALUES ('H0001', 50, 4, 'Hotel Transilvania', 'Cluj-Napoca');
INSERT INTO hotel VALUES ('H0002', 100, 5, 'Athenee Palace Hilton', 'Bucuresti');
INSERT INTO hotel VALUES ('H0003', 80, 4, 'Grand Hotel Continental', 'Bucuresti');
INSERT INTO hotel VALUES ('H0004', 60, 3, 'Hotel Bella Muzica', 'Brasov');
INSERT INTO hotel VALUES ('H0005', 62, 3, 'Articus', 'Craiova');
INSERT INTO hotel VALUES ('H0006', 120, 4, 'Hotel Ramada Parc', 'Bucuresti');
INSERT INTO hotel VALUES ('H0007', 40, 3, 'Hotel Ibis', 'Cluj-Napoca');
INSERT INTO hotel VALUES ('H0008', 90, 5, 'Hotel Kronwell', 'Brasov');
INSERT INTO hotel VALUES ('H0009', 70, 4, 'Hotel Mercure', 'Sibiu');
INSERT INTO hotel VALUES ('H0010', 110, 5, 'JW Marriott Bucharest', 'Bucuresti');
INSERT INTO hotel VALUES ('H0011', 55, 4, 'Hotel Continental Forum', 'Sibiu');
INSERT INTO hotel VALUES ('H0012', 150, 5, 'Radisson Blu Hotel', 'Bucuresti');
INSERT INTO hotel VALUES ('H0013', 30, 3, 'Hotel City Plaza', 'Cluj-Napoca');
INSERT INTO hotel VALUES ('H0014', 65, 4, 'Hotel Golden Tulip', 'Bucuresti');
INSERT INTO hotel VALUES ('H0015', 95, 5, 'Hilton Sibiu', 'Sibiu');
INSERT INTO hotel VALUES ('H0016', 74, 3, 'Green House', 'Craiova');
INSERT INTO hotel VALUES ('H0017', 120, 4, 'Hotel Crowne Plaza', 'Bucuresti');
INSERT INTO hotel VALUES ('H0018', 50, 3, 'Hotel Aro Palace', 'Brasov');
INSERT INTO hotel VALUES ('H0019', 85, 4, 'Hotel Novotel', 'Bucuresti');
INSERT INTO hotel VALUES ('H0020', 70, 4, 'Hotel Golden Tulip Ana Tower', 'Sibiu');
INSERT INTO hotel VALUES ('H0021', 40, 3, 'Hotel Premier', 'Sibiu');
INSERT INTO hotel VALUES ('H0022', 100, 5, 'Sheraton Bucharest Hotel', 'Bucuresti');
INSERT INTO hotel VALUES ('H0023', 61, 4, 'Europeca', 'Craiova');
INSERT INTO hotel VALUES ('H0024', 60, 4, 'Hotel Capitol', 'Timisoara');
INSERT INTO hotel VALUES ('H0025', 130, 5, 'InterContinental', 'Bucuresti');
INSERT INTO hotel VALUES ('H0026', 45, 3, 'Hotel Opera Plaza', 'Cluj-Napoca');
INSERT INTO hotel VALUES ('H0027', 75, 4, 'Hotel RIN Central', 'Bucuresti');
INSERT INTO hotel VALUES ('H0028', 95, 4, 'Hotel Ramada', 'Sibiu');
INSERT INTO hotel VALUES ('H0029', 110, 5, 'Hotel Hilton Garden Inn', 'Bucharest');
INSERT INTO hotel VALUES ('H0030', 50, 4, 'Hotel Traian', 'Iasi');
INSERT INTO hotel VALUES ('H0031', 85, 4, 'Ramada', 'Craiova');
------------------------------------------------------------------------------------------
INSERT INTO room VALUES ('RM001', 35, 'single', 2, 220, 'H0001');
INSERT INTO room VALUES ('RM002', 87, 'double', 5, 300, 'H0002');
INSERT INTO room VALUES ('RM003', 65, 'suite', 3, 350, 'H0003');
INSERT INTO room VALUES ('RM004', 55, 'single', 3, 240, 'H0004');
INSERT INTO room VALUES ('RM005', 35, 'single', 2, 250, 'H0005');
INSERT INTO room VALUES ('RM006', 101, 'single', 8, 300, 'H0006');
INSERT INTO room VALUES ('RM007', 25, 'double', 1, 280, 'H0007');
INSERT INTO room VALUES ('RM008', 79, 'single', 5, 650, 'H0008');
INSERT INTO room VALUES ('RM009', 65, 'double', 4, 287, 'H0009');
INSERT INTO room VALUES ('RM010', 105, 'single', 8, 300, 'H0010');
INSERT INTO room VALUES ('RM011', 40, 'double', 3, 250, 'H0011');
INSERT INTO room VALUES ('RM012', 132, 'single', 9, 320, 'H0012');
INSERT INTO room VALUES ('RM013', 20, 'single', 1, 260, 'H0013');
INSERT INTO room VALUES ('RM014', 49, 'double', 3, 320, 'H0014');
INSERT INTO room VALUES ('RM015', 85, 'single', 7, 350, 'H0015');
INSERT INTO room VALUES ('RM016', 58, 'suite', 3, 325, 'H0016');
INSERT INTO room VALUES ('RM017', 115, 'single', 9, 260, 'H0017');
INSERT INTO room VALUES ('RM018', 24, 'double', 1, 310, 'H0018');
INSERT INTO room VALUES ('RM019', 74, 'suite', 3, 380, 'H0019');
INSERT INTO room VALUES ('RM020', 63, 'single', 3, 320, 'H0020');
INSERT INTO room VALUES ('RM021', 33, 'double', 1, 280, 'H0021');
INSERT INTO room VALUES ('RM022', 99, 'single', 8, 240, 'H0022');
INSERT INTO room VALUES ('RM023', 41, 'double', 2, 320, 'H0023');
INSERT INTO room VALUES ('RM024', 51, 'single', 3, 210, 'H0024');
INSERT INTO room VALUES ('RM025', 122, 'single', 9, 220, 'H0025');
INSERT INTO room VALUES ('RM026', 31, 'double', 2, 280, 'H0026');
INSERT INTO room VALUES ('RM027', 18, 'single', 1, 300, 'H0027');
INSERT INTO room VALUES ('RM028', 73, 'single', 1, 300, 'H0028');
INSERT INTO room VALUES ('RM029', 95, 'double', 9, 360, 'H0029');
INSERT INTO room VALUES ('RM030', 18, 'single', 1, 270, 'H0030');
INSERT INTO room VALUES ('RM031', 70, 'single', 5, 245, 'H0031');
------------------------------------------------------------------------------------------
INSERT INTO reservation VALUES ('RE001', 'AurelianT', 'H0001', 'RM001', 'Confirmed', '2023-02-10', '2023-02-15');
INSERT INTO reservation VALUES ('RE002', 'LutuC', 'H0002', 'RM002', 'Pending', '2023-04-18', '2023-04-20');
INSERT INTO reservation VALUES ('RE003', 'VasilcoiuI', 'H0003', 'RM003', 'Confirmed', '2023-05-13', '2023-05-15');
INSERT INTO reservation VALUES ('RE004', 'VasileP', 'H0004', 'RM004', 'Pending', '2023-06-01', '2023-06-02');
INSERT INTO reservation VALUES ('RE005', 'SileC', 'H0005', 'RM005', 'Confirmed', '2023-07-15', '2023-07-17');
INSERT INTO reservation VALUES ('RE006', 'BogdanS', 'H0006', 'RM006', 'Pending', '2023-08-14', '2023-08-15');
INSERT INTO reservation VALUES ('RE007', 'MarianS', 'H0007', 'RM007', 'Confirmed', '2023-07-15', '2023-07-17');
INSERT INTO reservation VALUES ('RE008', 'PaulD', 'H0008', 'RM008', 'Pending', '2023-08-14', '2023-08-15');
INSERT INTO reservation VALUES ('RE009', 'ConstantinM', 'H0009', 'RM009', 'Confirmed', '2023-07-15', '2023-07-17');
INSERT INTO reservation VALUES ('RE010', 'CosminV', 'H0010', 'RM010', 'Pending', '2023-02-12', '2023-02-14');
INSERT INTO reservation VALUES ('RE011', 'IonT', 'H0011', 'RM011', 'Confirmed', '2023-10-15', '2023-10-17');
INSERT INTO reservation VALUES ('RE012', 'DanielaM', 'H0012', 'RM012', 'Confirmed', '2023-11-15', '2023-11-17');
INSERT INTO reservation VALUES ('RE013', 'AlexandruM', 'H0013', 'RM013', 'Pending', '2023-04-17', '2023-04-18');
INSERT INTO reservation VALUES ('RE014', 'CristinaU', 'H0014', 'RM014', 'Confirmed', '2023-07-15', '2023-07-17');
INSERT INTO reservation VALUES ('RE015', 'MonicaT', 'H0015', 'RM015', 'Confirmed', '2023-09-25', '2023-09-28');
INSERT INTO reservation VALUES ('RE016', 'AuraC', 'H0016', 'RM016', 'Confirmed', '2023-05-28', '2023-05-29');
INSERT INTO reservation VALUES ('RE017', 'AlexandruN', 'H0017', 'RM017', 'Pending', '2023-01-12', '2023-01-13');
INSERT INTO reservation VALUES ('RE018', 'AlexandruR', 'H0018', 'RM018', 'Pending', '2023-08-19', '2023-08-21');
INSERT INTO reservation VALUES ('RE019', 'MonicaB', 'H0019', 'RM019', 'Pending', '2023-04-10', '2023-04-12');
INSERT INTO reservation VALUES ('RE020', 'DanielN', 'H0020', 'RM020', 'Pending', '2023-08-02', '2023-08-03');
INSERT INTO reservation VALUES ('RE021', 'AdrianN', 'H0021', 'RM021', 'Pending', '2023-02-06', '2023-02-10');
INSERT INTO reservation VALUES ('RE022', 'CrinaP', 'H0022', 'RM022', 'Confirmed', '2023-01-15', '2023-01-20');
INSERT INTO reservation VALUES ('RE023', 'MarianG', 'H0023', 'RM023', 'Confirmed', '2023-11-15', '2023-11-17');
INSERT INTO reservation VALUES ('RE024', 'GabrielV', 'H0024', 'RM024', 'Pending', '2023-10-10', '2023-10-11');
INSERT INTO reservation VALUES ('RE025', 'GhitaM', 'H0025', 'RM025', 'Confirmed', '2023-03-01', '2023-03-08');
INSERT INTO reservation VALUES ('RE026', 'IonM', 'H0026', 'RM026', 'Pending', '2023-08-08', '2023-08-16');
INSERT INTO reservation VALUES ('RE027', 'RobertS', 'H0027', 'RM027', 'Confirmed', '2023-05-15', '2023-05-22');
INSERT INTO reservation VALUES ('RE028', 'MadalinT', 'H0028', 'RM028', 'Confirmed', '2023-12-15', '2023-12-22');
INSERT INTO reservation VALUES ('RE029', 'AurelianC', 'H0029', 'RM029', 'Confirmed', '2023-10-15', '2023-10-19');
INSERT INTO reservation VALUES ('RE030', 'ClaraS', 'H0030', 'RM030', 'Confirmed', '2023-12-15', '2023-12-20');
INSERT INTO reservation VALUES ('RE031', 'TatianaF', 'H0031', 'RM031', 'Confirmed', '2023-03-01', '2023-03-05');
--------------------------------------------------------------------------------------
INSERT INTO payment VALUES ('PM001', 'AurelianT', 'RE001', 220, 5, 1100, 'Paid');
INSERT INTO payment VALUES ('PM002', 'LutuC', 'RE002', 300, 2, 600, 'Declined');
INSERT INTO payment VALUES ('PM003', 'VasilcoiuI', 'RE003', 350, 2, 700, 'Paid');
INSERT INTO payment VALUES ('PM004', 'VasileP', 'RE004', 240, 1, 240, 'Declined');
INSERT INTO payment VALUES ('PM005', 'SileC', 'RE005', 250, 2, 500, 'Paid');
INSERT INTO payment VALUES ('PM006', 'BogdanS', 'RE006', 300, 1, 300, 'Declined');
INSERT INTO payment VALUES ('PM007', 'MarianS', 'RE007', 280, 2, 560, 'Paid');
INSERT INTO payment VALUES ('PM008', 'PaulD', 'RE008', 650, 1, 650, 'Declined');
INSERT INTO payment VALUES ('PM009', 'ConstantinM', 'RE009', 287, 2, 574, 'Paid');
INSERT INTO payment VALUES ('PM010', 'CosminV', 'RE010', 300, 2, 600, 'Declined');
INSERT INTO payment VALUES ('PM011', 'IonT', 'RE011', 250, 2, 500, 'Paid');
INSERT INTO payment VALUES ('PM012', 'DanielaM', 'RE012', 320, 2, 640, 'Declined');
INSERT INTO payment VALUES ('PM013', 'AlexandruM', 'RE013', 260, 1, 260, 'Paid');
INSERT INTO payment VALUES ('PM014', 'CristinaU', 'RE014', 320, 2, 640, 'Declined');
INSERT INTO payment VALUES ('PM015', 'MonicaT', 'RE015', 350, 3, 1050, 'Paid');
INSERT INTO payment VALUES ('PM016', 'AuraC', 'RE016', 325, 1, 325, 'Declined');
INSERT INTO payment VALUES ('PM017', 'AlexandruN', 'RE017', 260, 1, 260, 'Paid');
INSERT INTO payment VALUES ('PM018', 'AlexandruR', 'RE018', 310, 2, 620, 'Declined');
INSERT INTO payment VALUES ('PM019', 'MonicaB', 'RE019', 380, 2, 760, 'Paid');
INSERT INTO payment VALUES ('PM020', 'DanielN', 'RE020', 320, 1, 320, 'Declined');
INSERT INTO payment VALUES ('PM021', 'AdrianN', 'RE021', 280, 4, 1120, 'Paid');
INSERT INTO payment VALUES ('PM022', 'CrinaP', 'RE022', 240, 5, 1200, 'Declined');
INSERT INTO payment VALUES ('PM023', 'MarianG', 'RE023', 320, 2, 640, 'Paid');
INSERT INTO payment VALUES ('PM024', 'GabrielV', 'RE024', 210, 1, 210, 'Declined');
INSERT INTO payment VALUES ('PM025', 'GhitaM', 'RE025', 280, 8, 2240, 'Declined');
INSERT INTO payment VALUES ('PM026', 'IonM', 'RE026', 300, 7, 2100, 'Paid');
INSERT INTO payment VALUES ('PM027', 'RobertS', 'RE027', 300, 7, 2100, 'Declined');
INSERT INTO payment VALUES ('PM028', 'MadalinT', 'RE028', 360, 4, 1440, 'Paid');
INSERT INTO payment VALUES ('PM029', 'AurelianC', 'RE029', 270, 5, 1350, 'Declined');
INSERT INTO payment VALUES ('PM030', 'ClaraS', 'RE030', 245, 4, 980, 'Paid');
INSERT INTO payment VALUES ('PM031', 'TatianaF', 'RE031', 245, 3, 735, 'Paid');