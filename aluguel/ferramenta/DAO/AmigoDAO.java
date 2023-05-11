
package projeto.aluguel.ferramenta.DAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projeto.aluguel.ferramenta.Connection.ConnectionFactory;
import projeto.aluguel.ferramenta.Model.Amigo;

/**
 *
 * @author Wilyan
 */
public class AmigoDAO {
    
    public void Create(Amigo amigo){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            
            stmt = conn.prepareStatement("INSERT INTO tb_amigos (nome,telefone)VALUES(?,?)");
            stmt.setString(1, amigo.getNome());
            stmt.setInt(2, amigo.getTelefone());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro a o salvar: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stmt);
        }            
    }
    
    public List<Amigo> Read(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Amigo> amigos = new ArrayList<>();
        
        try {
            stmt = conn.prepareStatement("SELECT * FROM tb_amigos");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Amigo amigo = new Amigo();
                
                amigo.setId(rs.getInt("id"));
                amigo.setNome(rs.getString("nome"));
                amigo.setTelefone(rs.getInt("telefone"));
                
                amigos.add(amigo);
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erros a o ler os dados: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stmt, rs);
        } 
        
        return amigos;
        
    }
    
    public void Update(Amigo amigo){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            
            stmt = conn.prepareStatement("UPDATE tb_amigos SET nome = ?,telefone = ? WHERE id = ?");
            stmt.setString(1, amigo.getNome());
            stmt.setInt(2, amigo.getTelefone());
            stmt.setInt(3, amigo.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro a o atualizar: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stmt);
        }            
    }
    public void Delete(Amigo amigo){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            
            stmt = conn.prepareStatement("DELETE FROM tb_amigos WHERE id = ?");
            stmt.setInt(1, amigo.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro a o excluir: " + ex);
        }finally{
            
            ConnectionFactory.closeConnection(conn, stmt);
        }            
    }
    
}
