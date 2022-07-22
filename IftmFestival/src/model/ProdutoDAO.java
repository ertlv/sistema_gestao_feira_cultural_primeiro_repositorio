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
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author ertl
 */
public class ProdutoDAO {

    Scanner scanner = new Scanner(System.in);
    Produto produto = new Produto();

    public Produto adiciona(Produto elemento) {

        String sql = "insert into produto "
                + "(nome,descricao,preco)"
                + " values (?,?,?)";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getDescricao());
            stmt.setDouble(3, elemento.getPreco());

            stmt.execute();
            System.out.println("Sucesso");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public List<Produto> mostrarTodos() {
        String sql = "select * from produto";

        List<Produto> produtos = new ArrayList<>();

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");

                Produto prod = new Produto();
                prod.setId(id);
                prod.setNome(nome);
                prod.setDescricao(descricao);
                prod.setPreco(preco);

                produtos.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        imprimeLista(produtos);
        return produtos;
    }

    private void imprimeLista(List<Produto> produto) {

        for (Produto elemento : produto) {
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
        String sql = "select * from produto where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public Produto altera(Produto elemento) {
        String sql = "update produto set nome = ?, descricao = ?, preco = ? where id = ?";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNome());
            stmt.setString(1, elemento.getDescricao());
            stmt.setDouble(1, elemento.getPreco());
            stmt.setInt(1, elemento.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public boolean remover(int elemento) {
        String sql = "delete from produto where id = ?";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, elemento);

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
