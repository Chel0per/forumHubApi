create table cursos(

    id bigint not null auto_increment,
    nome varchar(255) not null,
    categoria varchar(100) not null,

    primary key(id)

);

create table usuarios(

    id bigint not null auto_increment,
    email varchar(255) not null,
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key(id)

);

create table topicos (

    id bigint not null auto_increment,
    titulo varchar(500) not null,
    mensagem text not null,
    data_criacao timestamp not null,
    status tinyint not null,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    foreign key(autor_id) references usuarios(id),
    foreign key(curso_id) references cursos(id)
);

create table perfis (

    id bigint not null auto_increment,
    nome varchar(255) not null,
    usuario_id bigint not null,

    primary key(id),
    foreign key(usuario_id) references usuarios(id)

);

create table respostas (

    id bigint not null auto_increment,
    solucao text not null,
    mensagem text not null,
    data_criacao timestamp not null,
    autor_id bigint not null,
    topico_id bigint not null,

    primary key(id),
    foreign key(autor_id) references usuarios(id),
    foreign key(topico_id) references topicos(id)

);