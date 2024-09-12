package org.kvittor.java.jdbc.models;

import org.kvittor.java.jdbc.repositorio.Repositorio;

import javax.lang.model.type.NullType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.kvittor.java.jdbc.util.ConexionBaseDatos.*;

public class UsuarioRepositorioImpl implements Repositorio<Usuario> {

    @Override
    public List<Usuario> listar() {
        List<Usuario> listaUsers = new ArrayList<>();
        try(Connection conn = getInstance();
            Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            while (rs.next()){
                listaUsers.add(crearUsuario(rs));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaUsers;

    }

    @Override
    public void agregar(Usuario usuario) {

        String sql = "INSERT INTO usuarios(username,password,email) VALUES(?,?,?)";

        try(Connection conn = getInstance();

            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,usuario.getUsername());
            stmt.setString(2,usuario.getPassword());
            stmt.setString(3,usuario.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {

        String sql = "DELETE FROM usuarios where usuarios.id = ?";

        try(Connection conn = getInstance();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            if(verificarTam(listar(),id)){
                throw new RuntimeException("El tam es mayor que el id");
            }else{
                stmt.setLong(1,id);
                stmt.executeUpdate();
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actualizar(Usuario usuario) {

        String sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id = ?";

        try(Connection conn = getInstance();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            if(verificarTam(listar(),usuario.getId())){
                throw new RuntimeException("El usuario con ese id no existeas!!");
            }else{


                stmt.setString(1,usuario.getUsername());
                stmt.setString(2,usuario.getPassword());
                stmt.setString(3,usuario.getEmail());
                stmt.setLong(4,usuario.getId());

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actualizarEntrada() {

        String sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id = ?";
        Scanner s = new Scanner(System.in);
        try(Connection conn = getInstance();
            PreparedStatement stmt = conn.prepareStatement(sql)) {


            System.out.println("============================");
            System.out.println("Listado para ver que usuario actualizar: ");

            listar().forEach(System.out::println);
            System.out.println("============================");

            System.out.println("Ingrese el id para actualizar");
            long id = s.nextLong();
            if(verificarTam(listar(),id)){
                throw new RuntimeException("El usuario con ese id no existe!!");
            }else{

                stmt.setLong(4,id);
                System.out.println("Ingrese el username: ");
                stmt.setString(1,s.next());
                System.out.println("Ingrese la password: ");
                stmt.setString(2,s.next());
                System.out.println("Ingrese el email: ");
                stmt.setString(3,s.next());

                s.close();
                stmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificarTam(List<Usuario> lista,Long id){

        return lista.size() < id;

    }

    public Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario user = new Usuario();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));

        return user;

    }

}
