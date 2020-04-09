package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {
    
    private int id;
    private int matricula;
    private boolean logado;
    
    public Usuario(){
        this.logado = false;
    }
    
    public void logar(String login, String senha) throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select id, matricula from usuario where matricula = '"+login+"' and senha = md5('"+senha+"')");
            rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getInt("id") >= 0){
                    this.id = rs.getInt("id");
                    this.matricula = rs.getInt("matricula");
                    this.logado = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
}
