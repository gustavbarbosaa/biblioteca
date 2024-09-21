import dao.UsuarioDAO;
import domain.Endereco;
import domain.Usuario;

public class Main {
    public static void main(String[] args) {
        var usuarioDAO = new UsuarioDAO();

        var endereco = Endereco.builder()
                .rua("Galdino vilante santos")
                .bairro("Casas populares")
                .numero("123")
                .cidade("Cajazeiras")
                .cep("58900000")
                .estado("PB")
                .build();

//        var usuario = Usuario.builder()
//                .nome("Gustavo Henrique")
//                .cpf("12332112312")
//                .email("gustavoaraujohab@gmail.com")
//                .telefone("12345")
//                .tipoUsuario("aluno")
//                .endereco(endereco)
//                .build();

//        usuarioDAO.save(usuario);

        var usuarios = usuarioDAO.getAllUsers();
        System.out.println(usuarios);
    }

}
