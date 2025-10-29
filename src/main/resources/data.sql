-- =====================
-- ARTISTS (40)
-- =====================
INSERT IGNORE INTO Artists (code, name, phone, email, country) VALUES
-- EDM
('A001', 'Martin Garrix', '+31 6 1234 5678', 'contact@martingarrix.com', 'Netherlands'),
('A002', 'Armin van Buuren', '+31 6 2345 6789', 'info@arminvanbuuren.com', 'Netherlands'),
('A003', 'Hardwell', '+31 6 3456 7890', 'booking@hardwell.com', 'Netherlands'),
('A004', 'David Guetta', '+33 6 4567 8901', 'management@davidguetta.com', 'France'),
('A005', 'Alok', '+55 11 9876 5432', 'contact@alokmusic.com', 'Brasil'),
('A006', 'Marshmello', '+1 323 555 1122', 'info@marshmellomusic.com', 'United States'),
('A007', 'Tiësto', '+31 6 6789 0123', 'booking@tiesto.com', 'Netherlands'),
('A008', 'Afrojack', '+31 6 7890 1234', 'contact@afrojack.com', 'Netherlands'),
('A009', 'Steve Aoki', '+1 310 555 3344', 'booking@steveaoki.com', 'United States'),
('A010', 'Dimitri Vegas & Like Mike', '+32 472 12 34 56', 'info@dimitrivegasandlikemike.com', 'Belgium'),

-- Drum & Bass
('A011', 'Andy C', '+44 7700 900001', 'contact@andyc.co.uk', 'United Kingdom'),
('A012', 'Noisia', '+31 6 9012 3456', 'info@noisia.nl', 'Netherlands'),
('A013', 'Camo & Krooked', '+43 660 123 4567', 'management@camokrooked.com', 'Austria'),
('A014', 'Netsky', '+32 470 22 33 44', 'booking@netskyofficial.com', 'Belgium'),
('A015', 'Sub Focus', '+44 7711 223344', 'info@subfocus.com', 'United Kingdom'),
('A016', 'Dimension', '+44 7722 334455', 'contact@dimensionuk.com', 'United Kingdom'),
('A017', 'Pendulum', '+61 412 345 678', 'management@pendulum.com', 'Australia'),
('A018', 'Chase & Status', '+44 7733 445566', 'info@chaseandstatus.co.uk', 'United Kingdom'),
('A019', 'Wilkinson', '+44 7744 556677', 'booking@wilkinson-music.com', 'United Kingdom'),
('A020', 'Hybrid Minds', '+44 7755 667788', 'contact@hybridminds.co.uk', 'United Kingdom'),

-- Hardstyle
('A021', 'Headhunterz', '+31 6 9988 7766', 'info@headhunterz.com', 'Netherlands'),
('A022', 'Sub Zero Project', '+31 6 8877 6655', 'booking@subzeroproject.com', 'Netherlands'),
('A023', 'D-Block & S-te-Fan', '+31 6 7766 5544', 'info@dblockstefan.com', 'Netherlands'),
('A024', 'Angerfist', '+31 6 6655 4433', 'contact@angerfist.nl', 'Netherlands'),
('A025', 'Miss K8', '+31 6 5544 3322', 'booking@missk8.com', 'Netherlands'),
('A026', 'Brennan Heart', '+32 470 998877', 'info@brennanheart.com', 'Belgium'),
('A027', 'Coone', '+31 6 4433 2211', 'contact@djcoone.com', 'Netherlands'),
('A028', 'Radical Redemption', '+31 6 3322 1100', 'info@radicalredemption.nl', 'Netherlands'),
('A029', 'Da Tweekaz', '+47 912 34 567', 'booking@datweekaz.com', 'Norway'),
('A030', 'Sefa', '+33 6 123 456 78', 'contact@sefamusic.com', 'France'),

-- Techno
('A031', 'Charlotte de Witte', '+32 471 23 45 67', 'booking@charlottedewitte.com', 'Belgium'),
('A032', 'Amelie Lens', '+32 472 89 01 23', 'info@amelielens.com', 'Belgium'),
('A033', 'Adam Beyer', '+46 70 123 4567', 'contact@adambeyer.se', 'Sweden'),
('A034', 'Nina Kraviz', '+7 495 123 4567', 'info@ninakraviz.com', 'Russia'),
('A035', 'Dax J', '+44 7700 111222', 'booking@daxj.co.uk', 'United Kingdom'),
('A036', 'Reinier Zonneveld', '+31 6 2233 4455', 'info@reinierzonneveld.com', 'Netherlands'),
('A037', 'I Hate Models', '+33 6 2233 4455', 'contact@ihatemodels.fr', 'France'),
('A038', '999999999', '+39 345 678 9012', 'info@999999999music.com', 'Italy'),
('A039', 'Paula Temple', '+44 7700 998877', 'contact@paulatemple.com', 'United Kingdom'),
('A040', 'Klangkuenstler', '+49 151 234 5678', 'info@klangkuenstler.de', 'Germany');

-- =====================
-- PERFORMANCES
-- =====================
INSERT IGNORE INTO Performances (code, date, start_time, end_time, artist_id, stage_id) VALUES
-- Day 1 (2025-07-12)
('P001', '2025-07-12', '22:00', '23:00', 1, 1),
('P002', '2025-07-12', '23:15', '00:15', 2, 1),
('P003', '2025-07-12', '00:30', '01:30', 3, 1),
('P004', '2025-07-12', '01:45', '02:45', 4, 1),
('P005', '2025-07-12', '03:00', '04:00', 7, 1),

('P006', '2025-07-12', '22:00', '23:00', 11, 2),
('P007', '2025-07-12', '23:15', '00:15', 12, 2),
('P008', '2025-07-12', '00:30', '01:30', 13, 2),
('P009', '2025-07-12', '01:45', '02:45', 15, 2),
('P010', '2025-07-12', '03:00', '04:00', 17, 2),

('P011', '2025-07-12', '22:00', '23:00', 21, 3),
('P012', '2025-07-12', '23:15', '00:15', 22, 3),
('P013', '2025-07-12', '00:30', '01:30', 23, 3),
('P014', '2025-07-12', '01:45', '02:45', 25, 3),
('P015', '2025-07-12', '03:00', '04:00', 27, 3),

('P016', '2025-07-12', '22:00', '23:00', 31, 4),
('P017', '2025-07-12', '23:15', '00:15', 32, 4),
('P018', '2025-07-12', '00:30', '01:30', 33, 4),
('P019', '2025-07-12', '01:45', '02:45', 34, 4),
('P020', '2025-07-12', '03:00', '04:00', 36, 4),

-- Day 2 (2025-07-13)
('P021', '2025-07-13', '22:00', '23:00', 5, 1),
('P022', '2025-07-13', '23:15', '00:15', 6, 1),
('P023', '2025-07-13', '00:30', '01:30', 8, 1),
('P024', '2025-07-13', '01:45', '02:45', 9, 1),
('P025', '2025-07-13', '03:00', '04:00', 10, 1),

('P026', '2025-07-13', '22:00', '23:00', 14, 2),
('P027', '2025-07-13', '23:15', '00:15', 16, 2),
('P028', '2025-07-13', '00:30', '01:30', 18, 2),
('P029', '2025-07-13', '01:45', '02:45', 19, 2),
('P030', '2025-07-13', '03:00', '04:00', 20, 2),

('P031', '2025-07-13', '22:00', '23:00', 24, 3),
('P032', '2025-07-13', '23:15', '00:15', 26, 3),
('P033', '2025-07-13', '00:30', '01:30', 28, 3),
('P034', '2025-07-13', '01:45', '02:45', 29, 3),
('P035', '2025-07-13', '03:00', '04:00', 30, 3),

('P036', '2025-07-13', '22:00', '23:00', 35, 4),
('P037', '2025-07-13', '23:15', '00:15', 37, 4),
('P038', '2025-07-13', '00:30', '01:30', 38, 4),
('P039', '2025-07-13', '01:45', '02:45', 39, 4),
('P040', '2025-07-13', '03:00', '04:00', 40, 4);
-- =====================
-- STAGES
-- =====================
INSERT IGNORE INTO Stages (code, name, capacity) VALUES
('STG01', 'The Oracle', 30000),
('STG02', 'The Blackout Arena', 12000),
('STG03', 'The Q-Dance Stage', 5000),
('STG04', 'The Katharsis Floor', 10000);

INSERT IGNORE INTO Sponsors (code, name, phone, email, contribution) VALUES
('SPN01', 'Red Bull', '+1 800-123-4567', 'contact@redbull.com', 50000.00),
('SPN02', 'Pioneer DJ', '+81 3-1234-5678', 'info@pioneerdj.com', 30000.00),
('SPN03', 'Spotify', '+1 800-555-9000', 'partnerships@spotify.com', 45000.00),
('SPN04', 'Heineken', '+31 20 523 9239', 'events@heineken.com', 40000.00),
('SPN05', 'Ultra Music', '+1 305-555-2121', 'label@ultramusic.com', 25000.00),
('SPN06', 'Beats by Dre', '+1 800-442-4000', 'promo@beatsbydre.com', 35000.00),
('SPN07', 'Coca-Cola', '+1 800-438-2653', 'media@coca-cola.com', 50000.00),
('SPN08', 'Smirnoff', '+44 20 1234 5678', 'brand@smirnoff.com', 30000.00),
('SPN09', 'YouTube Music', '+1 650-253-0000', 'partners@youtube.com', 48000.00),
('SPN10', 'Monster Energy', '+1 855-488-1212', 'sponsorships@monsterenergy.com', 42000.00),
('SPN11', 'Budweiser', '+1 800-342-5283', 'events@budweiser.com', 38000.00),
('SPN12', 'Tomorrowland Store', '+32 15 41 41 41', 'shop@tomorrowland.com', 20000.00),
('SPN13', 'Ableton', '+49 30 288 7630', 'info@ableton.com', 18000.00),
('SPN14', 'Native Instruments', '+49 30 6110350', 'contact@native-instruments.com', 15000.00),
('SPN15', 'Tidal', '+1 844-390-9434', 'partners@tidal.com', 22000.00),
('SPN16', 'Beatport', '+1 720-974-0900', 'support@beatport.com', 21000.00),
('SPN17', 'Corona', '+52 55 5262 3000', 'marketing@corona.com', 32000.00),
('SPN18', 'Spotify for Artists', '+1 212-389-1000', 'artists@spotify.com', 28000.00),
('SPN19', 'Dolby', '+1 415-558-0200', 'partnerships@dolby.com', 26000.00),
('SPN20', 'Bose', '+1 800-379-2073', 'promo@bose.com', 24000.00);

INSERT IGNORE INTO Stage_Sponsors (stage_id, sponsor_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 7), (1, 9), (1, 10),
(2, 4), (2, 8), (2, 10), (2, 11), (2, 14), (2, 16),
(3, 5), (3, 6), (3, 13), (3, 15), (3, 18), (3, 19),
(4, 12), (4, 14), (4, 16), (4, 17), (4, 19), (4, 20);
