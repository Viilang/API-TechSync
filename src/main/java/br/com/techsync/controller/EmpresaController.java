package br.com.techsync.controller;

import br.com.techsync.models.Empresa;
import br.com.techsync.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // Criar uma nova empresa
    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        Empresa novoEmpresa = empresaService.criarEmpresa(empresa);
        return ResponseEntity.ok(novoEmpresa);
    }

    // Editar uma empresa
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> editarEmpresa(@PathVariable int id, @RequestBody Empresa empresa) {
        Empresa empresaEditado = empresaService.editarEmpresa(id, empresa);
        return empresaEditado != null ? ResponseEntity.ok(empresaEditado) : ResponseEntity.notFound().build();
    }

    // Listar empresa pelo SEU PRÓPRIO ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> listarEmpresa(@PathVariable int id) {
        Empresa empresa = empresaService.listarEmpresa(id);
        return ResponseEntity.ok(empresa);
    }

    // --- ENDPOINT ADICIONADO AQUI ---
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Empresa> getEmpresaPorUsuarioId(@PathVariable int usuarioId) {
        Empresa empresa = empresaService.encontrarPorUsuarioId(usuarioId);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // ---------------------------------

    // Listar todas as empresas
    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresa = empresaService.listarEmpresas();
        return ResponseEntity.ok(empresa);
    }

    // Excluir uma empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirEmpresa(@PathVariable int id) {
        return empresaService.excluirEmpresa(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
