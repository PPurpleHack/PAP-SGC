package control;

import java.sql.SQLException;

public class Testes {
    public static void main(String[] args) throws SQLException{
        
        //------------------------------------------ESTABELECIMENTO------------------------------------------------------
        //CADASTRO ESTABELECIMENTO
        /*Estabelecimento estab = new Estabelecimento();
        estab.setCnpj("88.784.557/0001-01");
        estab.setNome("Estab Teste");
        estab.setCep("72311-300");
        estab.setNumero(1552);
        estab.setCidade("São Paulo");
        estab.setBairro("Samambaia Sul (Samambaia)");
        estab.setEstado("SP");
        estab.setPais("Brasil");
        estab.cadastrarEstabalecimento();
        System.out.println(estab.toString());*/
        
        //ATUALIZAR ESTABELECIMENTO
        Estabelecimento estab = new Estabelecimento(8);
        estab.setNome("Atualizado");
        if(estab.atualizarEstabelecimento()){
            System.out.println("ATUALIZOU");
        }
        
        //--------------------------------------------FUNCIONARIO-------------------------------------------------------
        //CADASTRO FUNCIONARIO
        /*Funcionario func = new Funcionario();
        func.setNome("Nicolas");
        func.setSobrenome("Oliveira");
        func.setCpf("438.814.708/75");
        func.setNumero(123);
        func.setCep("09401-060");
        func.setCidade("Ribeirão Pires");
        func.setBairro("Centro");
        func.setEstado("SP");
        func.setPais("Brasil");
        func.setEmail("n.silva.v.o@gmail.com");
        func.setFuncao(1);
        func.setEstabelecimento(8);
        func.cadastrarFuncionario();
        System.out.println(func.toString());*/
        
        //CADASTRO CONTA
        /*Funcionario func = new Funcionario(20);
        if(func.cadastrarConta("nicolas")){
            System.out.println("Cadastrou a conta");
        }*/
        
        
        //---------------------------------------------------SETOR-----------------------------------------------------
        //CADASTRO SETOR
        /*Setor set = new Setor();
        set.setNome("Carne");
        set.setEstabelecimento(8);
        set.cadastrarSetor();
        System.out.println(set.toString());*/
        
        //UPDATE SETOR
        /*Setor set = new Setor(2);
        set.setNome("Cachorro");
        if(set.atualizarSetor()){
            System.out.println("ATUALIZOU");
        }*/
        
        //DELETE SETOR
        /*Setor set = new Setor(2);
        if(set.excluirSetor()){
            System.out.println("EXLUIU");
        }*/
    }
}
