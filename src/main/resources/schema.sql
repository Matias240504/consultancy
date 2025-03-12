create table roles (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

create table users (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    direccion TEXT,
    password VARCHAR(255) NOT NULL,
    role_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE
);

-- Insertar roles por defecto
insert into roles (nombre) VALUES ('admin'), ('user');

-- Insertar un usuario administrador por defecto
insert into users (nombre, apellido, email, phone, direccion, password, role_id) 
values ('Admin', 'User', 'admin@example.com', '123456789', 'Dirección Admin', 'admin123', 1);
