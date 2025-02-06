-- Crear la tabla de canciones
CREATE TABLE IF NOT EXISTS song (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    artista VARCHAR(255),
    album VARCHAR(255),
    anno INT,
    genero VARCHAR(255)
);

-- Crear la tabla de listas de reproducción
CREATE TABLE IF NOT EXISTS playlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255)
);

-- Crear la tabla de relación entre listas de reproducción y canciones
CREATE TABLE IF NOT EXISTS playlist_song (
    playlist_id INT,
    song_id INT,
    FOREIGN KEY (playlist_id) REFERENCES playlist(id),
    FOREIGN KEY (song_id) REFERENCES song(id)
);

-- Insertar datos de ejemplo para las canciones
INSERT INTO song (titulo, artista, album, anno, genero) VALUES
('Shape of You', 'Ed Sheeran', 'Divide', 2017, 'Pop');

INSERT INTO song (titulo, artista, album, anno, genero) VALUES
('Blinding Lights', 'The Weeknd', 'After Hours', 2019, 'Pop');

INSERT INTO song (titulo, artista, album, anno, genero) VALUES
('Bohemian Rhapsody', 'Queen', 'A Night at the Opera', 1975, 'Rock');


-- Insertar datos de ejemplo para las listas de reproducción
INSERT INTO playlist (nombre, descripcion) VALUES
('Top Hits', 'Las canciones más populares de todos los tiempos');

INSERT INTO playlist (nombre, descripcion) VALUES
('Rock Classics', 'Una lista de clásicos del rock');


-- Insertar canciones en las listas de reproducción
-- Primero obtenemos los ids de las canciones que acabamos de insertar
-- Asumimos que los ids de las canciones son 1, 2, y 3
INSERT INTO playlist_song (playlist_id, song_id) VALUES
(1, 1),  -- 'Top Hits' contiene 'Shape of You'
(1, 2),  -- 'Top Hits' contiene 'Blinding Lights'
(2, 3);  -- 'Rock Classics' contiene 'Bohemian Rhapsody'
