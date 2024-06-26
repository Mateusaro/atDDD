package br.edu.infnet.cartaocredito.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioTests {

    @Test
    public void deve_criar_um_usuario_corretamente() throws Exception {
        Usuario usuario = new Usuario();
        //usuario.criar("Xpto", "teste@teste.com", "123456", "39048007011");
        Assertions.assertTrue(usuario.getCpf().equals("39048007011"));
        Assertions.assertTrue(usuario.getNome().equals("Xpto"));
        Assertions.assertTrue(usuario.getEmail().equals("teste@teste.com"));
        Assertions.assertTrue(usuario.getSenha().equals("MTIzNDU2"));
    }

    @Test
    public void nao_deve_criar_um_usuario_com_cpf_invalido() throws Exception {
        Usuario usuario = new Usuario();
       // usuario.criar("Xpto", "teste@teste.com", "123456", "39048007011");

        Assertions.assertThrows(Exception.class, () -> {
            //usuario.criar("Xpto", "teste@teste.com", "123456", "12347896311");
        });
    }



}
