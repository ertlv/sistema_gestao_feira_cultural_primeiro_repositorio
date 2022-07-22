/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import control.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import view.Tela;

/**
 *
 * @author ertl
 */
public class UsuarioDAO {

    Scanner scanner = new Scanner(System.in);
    Usuario usuario = new Usuario();

    public Usuario adiciona(Usuario elemento) {

        String sql = "insert into usuario "
                + "(nome,tipo,login,senha)"
                + " values (?,?,?,?)";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getTipo());
            stmt.setString(3, elemento.getLogin());
            stmt.setString(4, elemento.getSenha());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public List<Usuario> mostrarTodos() {
        String sql = "select * from usuario";

        List<Usuario> user = new ArrayList<>();

        try ( Connection connection = new ConexaoBanco().Conecta();  
                PreparedStatement stmt = connection.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                String login = rs.getString("login");
                String senha = rs.getString("senha");

                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(nome);
                usuario.setTipo(tipo);
                usuario.setLogin(login);
                usuario.setSenha(senha);

                user.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        imprimeLista(user);
        return user;
    }

    private void imprimeLista(List<Usuario> usuario) {

        for (Usuario elemento : usuario) {
            System.out.println(elemento);
        }
    }

    public Usuario buscaPorId(int code) {
        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement ps = createPreparedStatement(connection, code);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                String login = rs.getString("login");
                String senha = rs.getString("senha");

                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(nome);
                usuario.setTipo(tipo);
                usuario.setLogin(login);
                usuario.setSenha(senha);
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from usuario where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public Usuario altera(Usuario elemento) {
        String sql = "update usuario set nome = ?";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNome());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public boolean remover(int elemento) {
        String sql = "delete from usuario where id = ?";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, elemento);

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
