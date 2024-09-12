package org.kvittor.java.jdbc;

import org.kvittor.java.jdbc.models.Usuario;
import org.kvittor.java.jdbc.models.UsuarioRepositorioImpl;

public class Main {
    public static void main(String[] args) {

        UsuarioRepositorioImpl uri = new UsuarioRepositorioImpl();

        System.out.println("Usuarios agregados");
        // Usuarios ya agregados
//        uri.agregar(new Usuario("Kevin","12345","kevinvittor579@gmail.com"));
//        uri.agregar(new Usuario("Rodolfo","asd123","rodolfo@gmail.com"));
//        uri.agregar(new Usuario("Jose","11111","Jose@gmail.com"));
//        uri.agregar(new Usuario("Hernan","222222","Hernan@gmail.com"));

        uri.listar().forEach(System.out::println);
        System.out.println("Usuario actualizado");
        Usuario usuarioActualizar = new Usuario();
        usuarioActualizar.setUsername("Sofia");
        usuarioActualizar.setPassword("33333");
        usuarioActualizar.setEmail("Sofia@gmail.com");
        usuarioActualizar.setId(2L);
        uri.actualizar(usuarioActualizar);

        uri.listar().forEach(System.out::println);

        System.out.println("Eliminar usuario");
        //uri.eliminar(4L);
        uri.listar().forEach(System.out::println);

        System.out.println("Usuario actualizar por teclado");
        uri.actualizarEntrada();
        uri.listar().forEach(System.out::println);

    }
}