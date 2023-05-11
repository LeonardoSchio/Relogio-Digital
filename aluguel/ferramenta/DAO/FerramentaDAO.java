/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.aluguel.ferramenta.DAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projeto.aluguel.ferramenta.Connection.ConnectionFactory;
import projeto.aluguel.ferramenta.Model.Ferramenta;

public class FerramentaDAO {
    
    public void Create(Ferramenta ferramenta){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            
            stmt = conn.prepareStatement("INSERT INTO tb_ferramentas (ferramenta,marca,preço)VALUES(?,?,?)");
            stmt.setString(1, ferramenta.getNome());
            stmt.setString(2, ferramenta.getMarca());
            stmt.setDouble(3, ferramenta.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro a o salvar: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn ,stmt);
        }
    }

    public List<Ferramenta> Read(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Ferramenta> ferramentas = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_ferramentas");
            rs = stmt.executeQuery();
       
            while (rs.next()) {
                
                Ferramenta ferramenta = new Ferramenta();
                
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setNome(rs.getString("ferramenta"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setPreco(rs.getDouble("preço"));
                ferramentas.add(ferramenta);
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erros a o ler os dados: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stmt, rs);
        } 
        
        return ferramentas;

    }
    
    public void Update(Ferramenta ferramenta){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            
            stmt = conn.prepareStatement("UPDATE tb_ferramentas SET ferramenta = ?,marca = ?,preço = ? WHERE id = ?");
            stmt.setString(1, ferramenta.getNome());
            stmt.setString(2, ferramenta.getMarca());
            stmt.setDouble(3, ferramenta.getPreco());
            stmt.setInt(4, ferramenta.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro a o atualizar: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stmt);
        }            
    }
    public void Delete(Ferramenta ferramenta){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            
            stmt = conn.prepareStatement("DELETE FROM tb_ferramentas WHERE id = ?");
            stmt.setInt(1, ferramenta.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro a o excluir: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stmt);
        }            
    }
    
    
}