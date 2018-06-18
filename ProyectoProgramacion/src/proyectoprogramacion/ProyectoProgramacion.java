/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joshua
 */
public class ProyectoProgramacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            //Crear la conexion con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();

            //Insertamos una tabla
            statement.executeUpdate("drop table if exists americano");
            statement.executeUpdate("create table americano (precio float, name string)");
            statement.executeUpdate("insert into americano values(" + "2.9" + ", 'Hamburguesa')");
            statement.executeUpdate("insert into americano values(" + "3.5" + ", 'Bocata')");
            statement.executeUpdate("insert into americano values(" + "3.0" + ", 'Sandwich')");
            statement.executeUpdate("insert into americano values(" + "5.6" + ", 'Menu')");
            statement.executeUpdate("insert into americano values(" + "4.9" + ", 'Ensalada')");
            statement.executeUpdate("insert into americano values(" + "1.9" + ", 'Patatas')");
            statement.executeUpdate("insert into americano values(" + "2.5" + ", 'Perrito')");
            statement.executeUpdate("insert into americano values(" + "1.6" + ", 'Nestea')");
            statement.executeUpdate("insert into americano values(" + "1.0" + ", 'Agua')");
            statement.executeUpdate("insert into americano values(" + "1.5" + ", 'Kas')");

            Direccion dir = new Direccion();

            String calle = dir.selCalle();
            String local = dir.selRest(calle);

            //Mostramos el contenido de la tabla
            ResultSet rs = statement.executeQuery("select * from americano");
            while (rs.next()) {
                System.out.println("nombre = " + rs.getString("name"));
                System.out.println("precio = " + rs.getFloat("precio"));
            }

        } catch (SQLException e) {
            // Si salta el error de "out of memory", 
            // Seguramente significa que no se encuentra la base de datos
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Fallo de cierre de conexion.
                System.err.println(e);
            }
        }

    }

}
