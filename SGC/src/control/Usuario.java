package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {
    
    private int matricula;
    private String nome;
    private String email;
    private String tipoUsuario;
    private boolean logado;
    
    
    public Usuario(){
        this.logado = false;
    }
    
    public void logar(String login, String senha) throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT u.*, tu.descricao_curta tipoUsuario FROM usuario u " +
                                        "LEFT JOIN tipo_usuario tu ON u.tipo_usuario = tu.id " +
                                        "WHERE u.matricula = '"+login+"' " +
                                        "AND   u.senha = MD5('"+senha+"')");
            
            rs = stmt.executeQuery();
            while(rs.next()){
                    this.matricula = rs.getInt("matricula");
                    this.nome = rs.getString("nome");
                    this.email = rs.getString("email");
                    this.tipoUsuario = rs.getString("tipoUsuario");
                    this.logado = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public int getMatricula() {
        return matricula;
    }

    public boolean isLogado() {
        return logado;
    }
    
}
