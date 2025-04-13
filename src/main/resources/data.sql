-- Insertar categorias
INSERT INTO categories (category_name) VALUES ('Produccion Musical');
INSERT INTO categories (category_name) VALUES ('Mezcla');
INSERT INTO categories (category_name) VALUES ('Masterizacion');

-- Insertar cursos
-- Se asume que las categorias se insertan en el mismo orden y se asignan los IDs 1, 2 y 3 respectivamente.
INSERT INTO courses (course_title, course_category_id, course_description, course_num_videos, course_duration, course_cover, course_price)
VALUES ('Curso de Produccion Musical', 1, 'Aprende a producir musica profesionalmente.', 3, 180, 'https://cdn.pixabay.com/photo/2014/01/31/21/39/recording-255869_1280.jpg', 99.99);

INSERT INTO courses (course_title, course_category_id, course_description, course_num_videos, course_duration, course_cover, course_price)
VALUES ('Curso de Mezcla', 2, 'Domina las tecnicas de mezcla en estudios modernos.', 3, 150, 'https://cdn.pixabay.com/photo/2014/07/14/11/37/sound-studo-393042_1280.jpg', 89.99);

INSERT INTO courses (course_title, course_category_id, course_description, course_num_videos, course_duration, course_cover, course_price)
VALUES ('Curso de Masterizacion', 3, 'Descubre las practicas de masterizacion profesional.', 3, 120, 'https://cdn.pixabay.com/photo/2025/01/10/20/57/pro-audio-9324828_1280.jpg', 79.99);

-- Insertar videos para el Curso de Produccion Musical (course_id = 1)
INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Introduccion a la Produccion', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 1, 'Conceptos basicos de produccion musical.', 60);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Software y Equipos', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 1, 'Herramientas esenciales para producir musica.', 60);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Tecnicas Avanzadas', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 1, 'Tecnicas avanzadas en produccion.', 60);

-- Insertar videos para el Curso de Mezcla (course_id = 2)
INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Introduccion a la Mezcla', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 2, 'Fundamentos de la mezcla.', 50);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Ecualizacion y Compresion', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 2, 'Uso de ecualizadores y compresores.', 50);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Mezcla en Vivo', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 2, 'Consejos para mezclas en vivo.', 50);

-- Insertar videos para el Curso de Masterizacion (course_id = 3)
INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Introduccion a la Masterizacion', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 3, 'Fundamentos de la masterizacion.', 40);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Plugins y Herramientas', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 3, 'Herramientas y plugins para masterizar.', 40);

INSERT INTO videos (video_title, video_url, video_course_id, video_description, video_duration)
VALUES ('Masterizacion Profesional', 'https://youtu.be/BVhaxu3Qvzw?list=PLSZ3iv41NAgOgTcxcHmSSxRd2WLbcw5M7', 3, 'Consejos para masterizacion profesional.', 40);
