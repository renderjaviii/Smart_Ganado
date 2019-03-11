create table Lote(
	id integer,
	nombre varchar(255),
	primary key(id)
);
create table Raza(
	id integer,
	nombre varchar(255),
	primary key(id)
);
create table Proposito(
	id integer,
	nombre varchar(255),
	primary key(id)
);
create table Genero(
	id integer,
	nombre varchar(255),
	primary key(id)
);

create table Ganado(
	id integer,
	nombre varchar(255),
	id_Lote integer REFERENCES Lote(id) ON DELETE RESTRICT,
	id_Raza integer REFERENCES Raza(id) ON DELETE RESTRICT,
	id_Proposito integer REFERENCES Proposito(id) ON DELETE RESTRICT,
	id_Genero integer REFERENCES Genero(id) ON DELETE RESTRICT, 
	edad integer,
	peso float,
	foto varchar(255),
	detalles Varchar(255),
	primary key(id)
);


create table Finca(
	id integer,
	nombre varchar(255),
	ubicacion varchar(255),
	foto varchar(255),
	area varchar(255),
	primary key(id)
);
create table Finca_Ganado(
	id_Ganado integer REFERENCES Ganado(id) ON DELETE RESTRICT,
	id_Finca integer REFERENCES Finca(id) ON DELETE RESTRICT,
	primary key(id_Ganado, id_Finca)
);


create table Tipo_Evento(
	id integer,
	nombre varchar(255),
	primary key(id)
);
create table Evento(
	id_Finca integer REFERENCES Finca(id) ON DELETE RESTRICT,
	fecha date,
	id_Tipo_Evento integer REFERENCES Tipo_Evento(id) ON DELETE RESTRICT,
	nombre varchar(255),
	detalles varchar(255),
	encargado varchar(255),
	primary key(id_Finca, fecha, id_Tipo_Evento)
);

create table Rol(
	id integer,
	nombre integer,
	primary key(id)
);
create table Usuario(
	telefono integer,
	nombre varchar(255),
	correo varchar(255),
	id_Rol integer REFERENCES Rol(id) ON DELETE RESTRICT,
	foto varchar(255),
	primary key(Telefono)
);

create table Finca_Usuario(
	telefono_Usuario integer REFERENCES Usuario(telefono) ON DELETE RESTRICT,
	id_Finca integer REFERENCES Finca(id) ON DELETE RESTRICT,
	primary key(telefono_Usuario, id_Finca)
);


create table Backup(
	id integer,
	fecha date,
	telefono_Usuario integer REFERENCES Usuario(telefono) ON DELETE RESTRICT,
	primary key(id)
);

create table Tanque(
	id integer,
	nombre varchar(255),
	capacidad float,
	primary key(id)
);
create table Finca_Tanque(
	id_Finca integer REFERENCES Finca(id) ON DELETE RESTRICT,
	id_Tanque integer REFERENCES Tanque(id) ON DELETE RESTRICT,
	fecha date,
	produccion float,
	primary key(id_Finca, id_Tanque)
);

create table Tipo_Historia(
	id integer,
	telefono_Usuario integer REFERENCES Usuario(telefono) ON DELETE RESTRICT,
	nombre varchar(255),
	primary key(id, telefono_Usuario)
);
create table Cuaderno_Historias_Ganado(
	id integer,
	id_Ganado integer REFERENCES Ganado(id) ON DELETE RESTRICT,
	nombre_Historia varchar(255),
	fecha date,
	detalles varchar(255),
	primary key(id)
);
