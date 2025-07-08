// src/main/java/br/com/techsync/service/UsuarioService.java
package br.com.techsync.service;

import br.com.techsync.models.Usuario;
import br.com.techsync.repository.UsuarioRepository;
import br.com.techsync.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtUtil jwtUtil;

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado. Por favor, utilize outro.");
        }
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    public Usuario editarUsuario(int id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario u = usuarioExistente.get();
            u.setNome(usuario.getNome());
            u.setEmail(usuario.getEmail());
            // --- NOVOS CAMPOS PARA EDIÇÃO ---
            u.setTelefone(usuario.getTelefone());
            u.setCpf(usuario.getCpf());
            // --- FIM DOS NOVOS CAMPOS ---

            // A senha não deve ser atualizada aqui, a menos que seja um endpoint de "mudar senha"
            // Se o frontend enviar uma senha e você quiser que ela seja ignorada para edição de dados pessoais,
            // ou se for um campo de senha nulo/vazio, a lógica abaixo está correta.
            if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
                String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
                u.setSenha(senhaCriptografada);
            }

            return usuarioRepository.save(u);
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public boolean excluirUsuario(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Usuario buscarUsuarioId(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public String loginComJwt(String email, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            boolean senhaCorreta = passwordEncoder.matches(senha, usuario.getSenha());

            if (senhaCorreta) {
                return jwtUtil.gerarToken(email);
            }
        }
        return null;
    }

    public boolean resetSenha(String email, String novaSenha){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            String senhaCriptografada = passwordEncoder.encode(novaSenha);
            usuario.setSenha(senhaCriptografada);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public boolean gerarCodigo2FA(String email){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();

            String codigo2FA = String.format("%06d", new Random().nextInt(999999));
            usuario.setCodigo2FA(codigo2FA);

            usuarioRepository.save(usuario);

            System.out.println("Codigo gerado = " + codigo2FA);

            return true;
        }
        return false;
    }

    public boolean autenticar2FA(String email, String senha, String codigo2FA){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();

            boolean senhaValida = passwordEncoder.matches(senha, usuario.getSenha());
            boolean codigoValido = codigo2FA.equals(usuario.getCodigo2FA());

            return senhaValida && codigoValido;
        }
        return false;
    }
}