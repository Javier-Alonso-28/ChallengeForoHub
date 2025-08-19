CREATE TABLE IF NOT EXISTS topicos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  status VARCHAR(20) NOT NULL,
  autor VARCHAR(255) NOT NULL,
  curso VARCHAR(255) NOT NULL,
  CONSTRAINT uk_topico UNIQUE (titulo, mensaje(512))
);