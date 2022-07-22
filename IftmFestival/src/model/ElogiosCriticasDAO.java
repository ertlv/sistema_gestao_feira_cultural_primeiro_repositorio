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
 * @author ertl
 */
public class ElogiosCriticasDAO {

    public ElogiosCriticas adiciona(ElogiosCriticas elemento) {

        String sql = "insert into elogiosecriticas "
                + "(titulo,texto,autor)"
                + " values (?,?,?)";

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getTitulo());
            stmt.setString(2, elemento.getTexto());
            stmt.setString(3, elemento.getAutor());

            stmt.execute();
            System.out.println("Sucesso");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public List<ElogiosCriticas> mostrarTodos() {
        String sql = "select * from mural";

        List<ElogiosCriticas> ElogiosCriticass = new ArrayList<>();

        try ( Connection connection = new ConexaoBanco().Conecta();  PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String texto = rs.getString("texto");
                String autor = rs.getString("autor");

                ElogiosCriticas prod = new ElogiosCriticas();
                prod.setId(id);
                prod.setTitulo(titulo);
                prod.setTexto(texto);
                prod.setAutor(autor);

                ElogiosCriticass.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        imprimeLista(ElogiosCriticass);
        return ElogiosCriticass;
    }

    private void imprimeLista(List<ElogiosCriticas> ElogiosCriticass) {

        for (ElogiosCriticas elemento : ElogiosCriticass) {
            System.out.println(elemento);
        }
    }

}
