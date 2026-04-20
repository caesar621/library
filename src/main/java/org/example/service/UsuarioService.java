package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import org.example.infra.security.DadosCadastroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Usuario> listarUsuarios(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao);
    }

    public Usuario criarUsuario(DadosCadastroUsuario dados) {

        String senhaCrypto = passwordEncoder.encode(dados.senha());

        Usuario novoUsuario = new Usuario(dados.nome(), dados.login(), senhaCrypto);
        return usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizarUsuario(Long usuarioId, Usuario usuarioNome) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(usuarioNome.getNome());
        return usuarioRepository.save(usuario);
    }

    public String deletarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!usuario.getLivros().isEmpty()) {
            throw new RuntimeException("Não é possível deletar esse usuário, pois o mesmo ainda possui livros emprestados");
        }

        usuarioRepository.deleteById(usuarioId);

        return "Usuário deletado com sucesso";
    }


}
