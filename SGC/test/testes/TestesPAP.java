package testes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import control.Funcionario;
import java.sql.SQLException;

public class TestesPAP {
    
    public TestesPAP(){
    }
    
    @Test
    //TESTA O PROCESSO DE LOGIN
    public void testeLogin()throws SQLException{
        Funcionario f = new Funcionario();
        assertTrue(f.logar("2", "123"));
    }
    
    @Test
    //TESTA O PROCESSO DE LOGIN
    public void testeLogin2()throws SQLException{
        Funcionario f = new Funcionario();
        assertTrue(f.logar("2", "432"));
    }
    
    @Test
    //TESTA O TROCAR SENHA
    public void testeTrocarSenha()throws SQLException{
        Funcionario f = new Funcionario();
        f.setMatricula(0);
        assertFalse(f.trocarSenha("123"));
    }
    
    @Test
    //TESTA O TROCAR SENHA
    public void testeTrocarSenha2()throws SQLException{
        Funcionario f = new Funcionario();
        f.setMatricula(2);
        assertFalse(f.trocarSenha("123"));
    }
}
