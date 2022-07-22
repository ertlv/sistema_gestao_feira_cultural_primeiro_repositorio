/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.ProdutoDAO;
import model.Usuario;
import model.UsuarioDAO;
import model.Produto;
import model.Mural;
import model.MuralDAO;
import model.Pedido;
import model.ElogiosCriticas;
import model.ElogiosCriticasDAO;

/**
 *
 * @author ertl
 */
public class Tela {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario Login = new Usuario();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    Scanner scanner = new Scanner(System.in);
    MuralDAO mural = new MuralDAO();
    ElogiosCriticasDAO eloc = new ElogiosCriticasDAO();
    
    public void Login() {
        
        System.out.println("Entre com seus dados:\n");
        
        String um = "1";
        usuarioDAO.mostrarTodos();
        
        try {
            do {
                String[] loginSenha = login();
                Login = login(loginSenha[0], loginSenha[1]);
                if (Login != null) {
                    int id = 1;
                    if (Login.getTipo().equals(um)) {
                        menuPrincipal(1);
                    } else {
                        int idd = 2;
                        menuLeitura(2);
                    }
                } else {
                    System.out.println("Usuario não encontrado");
                }
            } while (Login == null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void menuPrincipal(int id) {
        
        int opc;
        
        System.out.println("\n Escolha a opcão desejada\n");
        System.out.println(" 1 - Produto");
        System.out.println(" 2 - Pedido");
        System.out.println(" 3 - Elogios e criticas");
        System.out.println(" 4 - Mural");
        System.out.println(" 0 - Sair do programa");
        opc = Integer.parseInt(scanner.nextLine());
        
        switch (opc) {
            case 1:
                int opc_prod;
                do {
                    System.out.println(" 1 - Cadastrar produto");
                    System.out.println(" 2 - Mostrar todos os produtos");
                    System.out.println(" 3 - Atualizar produto");
                    System.out.println(" 4 - Excluir produto");
                    System.out.println(" 5 - Voltar");
                    opc_prod = Integer.parseInt(scanner.nextLine());
                    
                    switch (opc_prod) {
                        case 1:
                            Produto p = criaprod();
                            if (produtoDAO.adiciona(p) != null) {
                                System.out.println("certo!");
                            } else {
                                System.out.println("ERRO");
                            }
                            break;
                        case 2:
                            produtoDAO.mostrarTodos();
                            break;
                        case 3:
                            produtoDAO.mostrarTodos();
                            //  produtoDAO.altera(ida);
                            break;
                        case 4:
                            produtoDAO.mostrarTodos();
                            
                            System.out.println("Informe o id");
                            
                            int ida = Integer.parseInt(scanner.nextLine());
                            produtoDAO.remover(opc_prod);
                            break;
                            case 5:
                            menuPrincipal(id);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    
                } while (opc_prod != 0);
                
                break;
            case 2:
                int opc_ped;
                do {
                    System.out.println(" 1 - Cadastrar pedido");
                    System.out.println(" 2 - Mostrar todos os pedidos");
                    System.out.println(" 3 - Atualizar pedido");
                    System.out.println(" 4 - Excluir pedido");
                    System.out.println(" 5 - Voltar");
                    opc_ped = Integer.parseInt(scanner.nextLine());
                } while (opc_ped != 0);
                break;
            case 3:
                int opc_ec;
                do {
                    System.out.println(" 1 - Cadastrar elogio ou critica");
                    System.out.println(" 2 - Mostrar todos os elogios ou criticas");
                    System.out.println(" 3 - Atualizar elogio ou critica");
                    System.out.println(" 4 - Excluir elogio ou critica");
                    System.out.println(" 5 - Voltar");
                    opc_ec = Integer.parseInt(scanner.nextLine());
                    
                    switch (opc_ec) {
                        case 1:
                            ElogiosCriticas ec = criaec();
                            if (eloc.adiciona(ec) != null) {
                                System.out.println("certo!");
                            } else {
                                System.out.println("ERRO");
                            }
                            break;
                        case 2:
                            
                            break;
                        case 5:
                            menuPrincipal(id);
                            break;
                        default:
                            throw new AssertionError();
                    }
                } while (opc_ec != 0);
                break;
            case 4:
                int opc_m;
                do {
                    System.out.println(" 1 - Cadastrar no mural");
                    System.out.println(" 2 - Mostrar todo o mural");
                    System.out.println(" 3 - Atualizar mural");
                    System.out.println(" 4 - Excluir do mural");
                    System.out.println(" 0 - Voltar");
                    opc_m = Integer.parseInt(scanner.nextLine());
                    
                    switch (opc_m) {
                        case 1:
                            Mural m = criam();
                            if (mural.adiciona(m) != null) {
                                System.out.println("certo!");
                            } else {
                                System.out.println("ERRO");
                            }
                            break;
                        case 2:
                            mural.mostrarTodos();
                            break;
                        default:
                            throw new AssertionError();
                    }
                } while (opc_m != 0);
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    public void menuLeitura(int idd) {
        
    }
    
    private Produto criaprod() {
        Produto p = new Produto();
        
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Descricao: ");
        String desc = scanner.nextLine();
        System.out.println("Preco: ");
        double preco = Double.parseDouble(scanner.nextLine());
        
        p.setNome(nome);
        p.setDescricao(desc);
        p.setPreco(0);
        
        return p;
    }
    
    private Produto alteraprod() {
        return null;
    }
    
    private Mural criam() {
        Mural m = new Mural();
        
        System.out.println("Titulo: ");
        String ti = scanner.nextLine();
        System.out.println("Texto: ");
        String texto = scanner.nextLine();
        System.out.println("autor: ");
        String autor = scanner.nextLine();
        
        m.setTitulo(ti);
        m.setTexto(texto);
        m.setAutor(autor);
        return m;
    }
    
    private Mural alteram() {
        return null;
    }
    
    private ElogiosCriticas criaec() {
        ElogiosCriticas ec = new ElogiosCriticas();
        
        System.out.println("Titulo: ");
        String ti = scanner.nextLine();
        System.out.println("Texto: ");
        String texto = scanner.nextLine();
        System.out.println("autor: ");
        String autor = scanner.nextLine();
        
        ec.setTitulo(ti);
        ec.setTexto(texto);
        ec.setAutor(autor);
        return ec;
    }
    
    private Pedido criaped() {
        Pedido ped = new Pedido();
        
        System.out.println("ID PRODUTO: ");
        int ti = Integer.parseInt(scanner.nextLine());
        System.out.println("qtd: ");
        int qtd = Integer.parseInt(scanner.nextLine());
        System.out.println("valor total: ");
        double vt = Integer.parseInt(scanner.nextLine());
        System.out.println("estadp: ");
        String estado = scanner.nextLine();

        ped.setIdproduto(ti);
        ped.setQtd(qtd);
        ped.setValortotal(vt);
        ped.setEstado(estado);
        
        return ped;
    }
    
    public Usuario login(String login, String senha) throws SQLException, Exception {;
        Usuario responseLogin = new Usuario();
        
        List<Usuario> vetUsuario = usuarioDAO.mostrarTodos();
        
        for (Usuario usuario : vetUsuario) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                responseLogin = usuario;
                return responseLogin;
            }
        }
        
        return null;
    }
    
    public String[] login() {
        String[] loginSenha = new String[2];
        System.out.println("Login:");
        loginSenha[0] = scanner.nextLine();
        System.out.println("Senha:");
        loginSenha[1] = scanner.nextLine();
        return loginSenha;
    }
    
}
