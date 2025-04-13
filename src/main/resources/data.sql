-- Insertar categorías
INSERT INTO categories (category_name) VALUES ('Producción Musical');
INSERT INTO categories (category_name) VALUES ('Mezcla');
INSERT INTO categories (category_name) VALUES ('Masterización');

-- Insertar cursos (asumiendo que las categorías se insertan en ese orden y obtienen IDs 1, 2 y 3)
INSERT INTO courses (course_title, course_category_id, course_description, course_num_videos, course_duration, course_cover, course_price)
VALUES ('Curso de Producción Musical', 1, 'Aprende a producir música profesionalmente.', 3, 180, 'produccion.jpg', 99.99);

INSERT INTO courses (course_title, course_category_id, course_description, course_num_videos, course_duration, course_cover, course_price)
VALUES ('Curso de Mezcla', 2, 'Domina las técnicas de mezcla en estudios modernos.', 3, 150, 'mezcla.jpg', 89.99);

INSERT INTO courses (course_title, course_category_id, course_description, course_num_videos, course_duration, course_cover, course_price)
VALUES ('Curso de Masterización', 3, 'Aprende el arte de masterizar tus producciones.', 3, 120, 'masterizacion.jpg', 79.99);

-- Insertar videos para el Curso de Producción Musical (course_id = 1)
INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Introducción a la Producción Musical', 'https://example.com/video1', 1, 'Conceptos básicos de la producción musical.', 60);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Software y Equipos', 'https://example.com/video2', 1, 'Herramientas esenciales para producir música.', 60);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Técnicas Avanzadas', 'https://example.com/video3', 1, 'Explora técnicas avanzadas de producción.', 60);

-- Insertar videos para el Curso de Mezcla (course_id = 2)
INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Introducción a la Mezcla', 'https://example.com/mezcla1', 2, 'Conceptos básicos de la mezcla en estudio.', 50);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Uso de Ecualizadores y Compresores', 'https://example.com/mezcla2', 2, 'Técnicas de ecualización y compresión.', 50);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Mezcla en Vivo', 'https://example.com/mezcla3', 2, 'Consejos y técnicas para mezclas en directo.', 50);

-- Insertar videos para el Curso de Masterización (course_id = 3)
INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Introducción a la Masterización', 'https://example.com/masterizacion1', 3, 'Conceptos básicos de masterización.', 40);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Plugins y Herramientas', 'https://example.com/masterizacion2', 3, 'Uso de plugins en el proceso de masterización.', 40);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Consejos Profesionales', 'https://example.com/masterizacion3', 3, 'Mejores prácticas de masterización.', 40);
