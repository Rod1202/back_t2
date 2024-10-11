Para que funcione correctamente, es importante crear una base de datos local con MySQL : 

Este es el script : 


create database examen_t2;

use examen_t2;


create table tb_programador

(  idprogramador int primary key auto_increment,

    nombre varchar(50),
    
    apellido varchar(50),
    
    hijos int,
    
    sueldo double);
  
