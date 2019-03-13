create table Lote(
	id serial,
	nombre varchar(255),
	primary key(id)
);
create table Raza(
	id serial,
	nombre varchar(255),
	primary key(id)
);
create table Proposito(
	id serial,
	nombre varchar(255),
	primary key(id)
);
create table Genero(
	id serial,
	nombre varchar(255),
	primary key(id)
);

create table Ganado(
	id integer,
	nombre varchar(255),
	id_Lote integer REFERENCES Lote(id) ON DELETE CASCADE,
	id_Raza integer REFERENCES Raza(id) ON DELETE CASCADE,
	id_Proposito integer REFERENCES Proposito(id) ON DELETE CASCADE,
	id_Genero integer REFERENCES Genero(id) ON DELETE CASCADE, 
	edad integer,
	peso float,
	foto varchar(255),
	detalles Varchar(255),
	primary key(id)
);


create table Finca(
	id serial,
	nombre varchar(255),
	ubicacion varchar(255),
	foto varchar,
	area float,
	primary key(id)
);
create table Finca_Ganado(
	id_Ganado integer REFERENCES Ganado(id) ON DELETE CASCADE,
	id_Finca integer REFERENCES Finca(id) ON DELETE CASCADE,
	primary key(id_Ganado, id_Finca)
);


create table Tipo_Evento(
	id serial,
	nombre varchar(255),
	primary key(id)
);
create table Evento(
	fecha date,
	id_Finca integer REFERENCES Finca(id) ON DELETE CASCADE,
	id_Tipo_Evento integer REFERENCES Tipo_Evento(id) ON DELETE CASCADE,
	nombre varchar(255),
	detalles varchar(255),
	encargado varchar(255),
	primary key(id_Finca, fecha, id_Tipo_Evento)
);

create table Rol(
	id serial,
	nombre varchar(255),
	primary key(id)
);
create table Usuario(
	telefono integer,
	id_Rol integer REFERENCES Rol(id) ON DELETE CASCADE,
	nombre varchar(255),
	password varchar(255),
	correo varchar(255),
	foto varchar(255),
	primary key(Telefono)
);

create table Finca_Usuario(
	telefono_Usuario integer REFERENCES Usuario(telefono) ON DELETE CASCADE,
	id_Finca integer REFERENCES Finca(id) ON DELETE CASCADE,
	primary key(telefono_Usuario, id_Finca)
);


create table Backup(
	id serial,
	fecha date,
	telefono_Usuario integer REFERENCES Usuario(telefono) ON DELETE CASCADE,
	primary key(id)
);

create table Tanque(
	id serial,
	nombre varchar(255),
	capacidad float,
	primary key(id)
);
create table Finca_Tanque(
	id_Finca integer REFERENCES Finca(id) ON DELETE CASCADE,
	id_Tanque integer REFERENCES Tanque(id) ON DELETE CASCADE,
	fecha date,
	produccion float,
	primary key(id_Finca, id_Tanque, fecha)
);

create table Tipo_Historia(
	id serial,
	telefono_Usuario integer REFERENCES Usuario(telefono) ON DELETE CASCADE,
	nombre varchar(255),
	primary key(id, telefono_Usuario)
);
create table Cuaderno_Historias_Ganado(
	id serial,
	id_Ganado integer REFERENCES Ganado(id) ON DELETE CASCADE,
	id_Tipo_Historia REFERENCES Tipo_Historia(id) ON DELETE CASCADE,
	nombre_Historia varchar(255),
	fecha date,
	detalles varchar(255),
	primary key(id)
);
