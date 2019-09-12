/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cinema;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoCinema {
     public static boolean inserir(Cinema objeto) {
        String sql = "INSERT INTO cinema (endereco, capacidade) VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getEndereco());
            ps.setInt(2, objeto.getCapacidade());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
      public static void main(String[] args) {
        Cinema objeto = new Cinema();
        objeto.setEndereco("Arco-Íris, Passo Fundo, RS");
        objeto.setCapacidade(12);
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
      
      public static List<Cinema> consultar() {
        List<Cinema> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT endereco, capacidade, codigo FROM cinema";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cinema objeto = new Cinema();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setEndereco(rs.getString("endereco"));
                objeto.setCapacidade(rs.getInt("capacidade"));
                objeto.setCodigo(rs.getInt("codigo"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
}
