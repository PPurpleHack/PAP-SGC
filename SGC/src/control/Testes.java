package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Testes {
    public static void main(String[] args) throws SQLException{
        
        //------------------------------------------ESTABELECIMENTO------------------------------------------------------
        //CARREGA ESTABELECIMENTO
        /*Estabelecimento estab = new Estabelecimento(8);
        System.out.println(estab.getTelefone().size());*/
        
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
        ArrayList<String> telefone =  new ArrayList();
        telefone.add("011 22223333");
        telefone.add("011 22223333");
        telefone.add("011 22223333");
        estab.setTelefone(telefone);
        estab.cadastrarEstabalecimento();
        System.out.println(estab.toString());*/
        
        //CADASTRO TELEFONE ESTABELECIMENTO
        /*Estabelecimento estab = new Estabelecimento(10);
        ArrayList<String> telefone =  new ArrayList();
        telefone.add("011 22223333");
        telefone.add("011 22223333");
        telefone.add("011 22223333");
        telefone.add("011 22223333");
        estab.setTelefone(telefone);
        System.out.println(estab.getTelefone());
        estab.cadastraTelefone();*/
        
        //ATUALIZAR ESTABELECIMENTO
        //Estabelecimento estab = new Estabelecimento(8);

        //EXCLUIR ESTABELECIMENTO
        //Estabelecimento estab = new Estabelecimento(/*Colocar um id de um estabelecimento*/);
        /*switch(estab.excluirEstabelecimento()){
            case -1:
                System.out.println("Erro não esperado durante a execução");
                break;
            case 1:
                System.out.println("Excluiu");
                break;
            case 1064:
                System.out.println("Foi encontrado um erro de sintaxe");
                break;
            case 1451:
                System.out.println("Existe informações ligadas a esse dado que você está tentando excluir, "
                        + "por isso não é possível realizar a exclusão");
                break;
            default:
                System.out.println("Ocorreu um erro ainda não mapeado");
                break;
        }*/
        
        //LISTA ESTABELECIMENTO
        Estabelecimento estab = new Estabelecimento();
        Map<String, String> filtros = new HashMap<String, String>();
        
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
        
        //LOGAR
        /*Funcionario func = new Funcionario();
        if(func.logar(19, "123")){
            System.out.println("Logou");
        }
        if(!func.logar(19, "8723")){
            System.out.println("Não logou");
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
