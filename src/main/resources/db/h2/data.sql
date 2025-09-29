-- Vets
INSERT INTO vets (first_name, last_name) VALUES ('James', 'Carter');
INSERT INTO vets (first_name, last_name) VALUES ('Helen', 'Leary');
INSERT INTO vets (first_name, last_name) VALUES ('Linda', 'Douglas');
INSERT INTO vets (first_name, last_name) VALUES ('Rafael', 'Ortega');
INSERT INTO vets (first_name, last_name) VALUES ('Henry', 'Stevens');
INSERT INTO vets (first_name, last_name) VALUES ('Sharon', 'Jenkins');

-- Specialties
INSERT INTO specialties (name) VALUES ('radiology');
INSERT INTO specialties (name) VALUES ('surgery');
INSERT INTO specialties (name) VALUES ('dentistry');
INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

-- Pet Types
INSERT INTO types (name) VALUES ('cat');
INSERT INTO types (name) VALUES ('dog');
INSERT INTO types (name) VALUES ('lizard');
INSERT INTO types (name) VALUES ('snake');
INSERT INTO types (name) VALUES ('bird');
INSERT INTO types (name) VALUES ('hamster');

-- Owners
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES ('Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

-- Pets
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Leo', '2010-09-07', 1, 1);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Basil', '2012-08-06', 6, 2);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Rosy', '2011-04-17', 2, 3);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Jewel', '2010-03-07', 2, 3);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Iggy', '2010-11-30', 3, 4);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('George', '2010-01-20', 4, 5);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Samantha', '2012-09-04', 1, 6);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Max', '2012-09-04', 1, 6);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Lucky', '2011-08-06', 5, 7);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Freddy', '2010-03-09', 5, 9);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Lucky', '2010-06-24', 2, 10);
INSERT INTO pets (name, birth_date, type_id, owner_id) VALUES ('Sly', '2012-06-08', 1, 10);

-- Visits
INSERT INTO visits (pet_id, visit_date, description) VALUES (7, '2013-01-01', 'rabies shot');
INSERT INTO visits (pet_id, visit_date, description) VALUES (8, '2013-01-02', 'rabies shot');
INSERT INTO visits (pet_id, visit_date, description) VALUES (8, '2013-01-03', 'neutered');
INSERT INTO visits (pet_id, visit_date, description) VALUES (7, '2013-01-04', 'spayed');


-- ================================================================= --
-- ここから新しい機能のサンプルデータを追加
-- ================================================================= --

-- Hospital Reservations
INSERT INTO hospital_reservations (pet_id, vet_id, reservation_time, description) VALUES (1, 1, '2025-08-10 10:00:00', 'Annual checkup');
INSERT INTO hospital_reservations (pet_id, vet_id, reservation_time, description) VALUES (3, 2, '2025-08-11 11:30:00', 'Vaccination');

-- Trimming Appointments
INSERT INTO trimming_appointments (pet_id, appointment_time, course, trimmer_name) VALUES (2, '2025-08-12 14:00:00', 'Shampoo & Cut Course', 'Alice');

-- Hotel Stays
INSERT INTO hotel_stays (pet_id, check_in_date, check_out_date, room_number) VALUES (7, '2025-09-01', '2025-09-05', 'A-01');
INSERT INTO hotel_stays (pet_id, check_in_date, check_out_date, room_number) VALUES (8, '2025-09-03', '2025-09-04', 'B-02');

-- Billings (for Hotel Stay ID: 2)
INSERT INTO billings (stay_id, amount, payment_date) VALUES (2, 5000.00, '2025-09-04');

-- Prescriptions (for Hospital Reservation ID: 1)
INSERT INTO prescriptions (reservation_id, medicine_name, quantity) VALUES (1, 'Medicine-A', 5);
INSERT INTO prescriptions (reservation_id, medicine_name, quantity) VALUES (1, 'Medicine-B', 2);