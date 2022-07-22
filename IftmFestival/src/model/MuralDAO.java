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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MuralDAO {

    public Mural adiciona(Mural elemento) {

        String sql = "insert into mural "
                + "(titulo,texto, autor)"
                + " values (?,?,?)";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getTitulo());
            stmt.setString(2, elemento.getTexto());
            stmt.setString(3, elemento.getTexto());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public List<Mural> mostrarTodos() {
        String sql = "select * from mural";

        List<Mural> murals = new ArrayList<>();

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String texto = rs.getString("texto");
                String autor = rs.getString("autor");

                Mural prod = new Mural();
                prod.setId(id);
                prod.setTitulo(titulo);
                prod.setTexto(texto);
                prod.setAutor(autor);

                murals.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        imprimeLista(murals);
        return murals;
    }

    private void imprimeLista(List<Mural> murals) {

        for (Mural elemento : murals) {
            System.out.println(elemento);
        }
    }

}
