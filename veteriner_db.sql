-- Veteriner Yönetim Sistemi Örnek Veri
-- PostgreSQL için hazırlanmıştır

-- Müşteriler
INSERT INTO customers (name, phone, mail, address, city) VALUES
('Ahmet Yılmaz', '05551234567', 'ahmet@email.com', 'Atatürk Cad. No:123', 'İstanbul'),
('Fatma Demir', '05559876543', 'fatma@email.com', 'İnönü Sok. No:45', 'Ankara'),
('Mehmet Kaya', '05551112233', 'mehmet@email.com', 'Cumhuriyet Mah. No:67', 'İzmir'),
('Ayşe Özkan', '05554445566', 'ayse@email.com', 'Gazi Cad. No:89', 'Bursa'),
('Ali Çelik', '05557778899', 'ali@email.com', 'Yeni Mah. No:12', 'Antalya');

-- Doktorlar
INSERT INTO doctors (name, phone, mail, address, city) VALUES
('Dr. Ayşe Demir', '05559876543', 'ayse.demir@vet.com', 'Veteriner Cad. No:45', 'İstanbul'),
('Dr. Mehmet Yılmaz', '05551234567', 'mehmet.yilmaz@vet.com', 'Sağlık Sok. No:78', 'Ankara'),
('Dr. Fatma Kaya', '05551112233', 'fatma.kaya@vet.com', 'Hastane Cad. No:90', 'İzmir'),
('Dr. Ali Özkan', '05554445566', 'ali.ozkan@vet.com', 'Klinik Mah. No:34', 'Bursa'),
('Dr. Zeynep Çelik', '05557778899', 'zeynep.celik@vet.com', 'Veteriner Sok. No:56', 'Antalya');

-- Hayvanlar
INSERT INTO animals (name, species, breed, gender, colour, date_of_birth, customer_id) VALUES
('Pamuk', 'Kedi', 'Tekir', 'Dişi', 'Gri', '2020-01-15', 1),
('Karabaş', 'Köpek', 'Golden Retriever', 'Erkek', 'Sarı', '2019-03-20', 1),
('Mırnav', 'Kedi', 'Sokak Kedisi', 'Erkek', 'Beyaz', '2021-05-10', 2),
('Patron', 'Köpek', 'Alman Kurdu', 'Erkek', 'Siyah', '2018-07-12', 3),
('Minnoş', 'Kedi', 'Pers', 'Dişi', 'Turuncu', '2022-02-28', 4),
('Rex', 'Köpek', 'Labrador', 'Erkek', 'Kahverengi', '2020-11-05', 5),
('Boncuk', 'Kedi', 'British Shorthair', 'Dişi', 'Mavi', '2021-08-15', 2),
('Max', 'Köpek', 'Poodle', 'Erkek', 'Beyaz', '2019-12-03', 3);

-- Müsait Günler
INSERT INTO available_dates (available_date, doctor_id) VALUES
('2024-01-15', 1),
('2024-01-16', 1),
('2024-01-17', 1),
('2024-01-15', 2),
('2024-01-16', 2),
('2024-01-18', 2),
('2024-01-15', 3),
('2024-01-19', 3),
('2024-01-20', 3),
('2024-01-15', 4),
('2024-01-21', 4),
('2024-01-22', 4),
('2024-01-15', 5),
('2024-01-23', 5),
('2024-01-24', 5);

-- Aşılar
INSERT INTO vaccines (name, code, protection_start_date, protection_finish_date, animal_id) VALUES
('Kuduz Aşısı', 'KUDUZ001', '2024-01-01', '2025-01-01', 1),
('Karma Aşı', 'KARMA001', '2024-01-05', '2024-07-05', 1),
('Kuduz Aşısı', 'KUDUZ002', '2024-01-02', '2025-01-02', 2),
('Karma Aşı', 'KARMA002', '2024-01-06', '2024-07-06', 2),
('Kuduz Aşısı', 'KUDUZ003', '2024-01-03', '2025-01-03', 3),
('Karma Aşı', 'KARMA003', '2024-01-07', '2024-07-07', 3),
('Kuduz Aşısı', 'KUDUZ004', '2024-01-04', '2025-01-04', 4),
('Karma Aşı', 'KARMA004', '2024-01-08', '2024-07-08', 4),
('Kuduz Aşısı', 'KUDUZ005', '2024-01-05', '2025-01-05', 5),
('Karma Aşı', 'KARMA005', '2024-01-09', '2024-07-09', 5),
('Kuduz Aşısı', 'KUDUZ006', '2024-01-06', '2025-01-06', 6),
('Karma Aşı', 'KARMA006', '2024-01-10', '2024-07-10', 6),
('Kuduz Aşısı', 'KUDUZ007', '2024-01-07', '2025-01-07', 7),
('Karma Aşı', 'KARMA007', '2024-01-11', '2024-07-11', 7),
('Kuduz Aşısı', 'KUDUZ008', '2024-01-08', '2025-01-08', 8),
('Karma Aşı', 'KARMA008', '2024-01-12', '2024-07-12', 8);

-- Randevular
INSERT INTO appointments (appointment_date, doctor_id, animal_id) VALUES
('2024-01-15 10:00:00', 1, 1),
('2024-01-15 11:00:00', 1, 2),
('2024-01-15 14:00:00', 1, 3),
('2024-01-16 09:00:00', 1, 4),
('2024-01-16 10:00:00', 1, 5),
('2024-01-15 10:00:00', 2, 6),
('2024-01-15 11:00:00', 2, 7),
('2024-01-16 09:00:00', 2, 8),
('2024-01-15 10:00:00', 3, 1),
('2024-01-15 11:00:00', 3, 2),
('2024-01-19 09:00:00', 3, 3),
('2024-01-19 10:00:00', 3, 4),
('2024-01-15 10:00:00', 4, 5),
('2024-01-15 11:00:00', 4, 6),
('2024-01-21 09:00:00', 4, 7),
('2024-01-21 10:00:00', 4, 8),
('2024-01-15 10:00:00', 5, 1),
('2024-01-15 11:00:00', 5, 2),
('2024-01-23 09:00:00', 5, 3),
('2024-01-23 10:00:00', 5, 4); 