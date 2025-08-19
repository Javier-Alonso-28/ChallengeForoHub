package com.ForoHub.controller;

import com.ForoHub.dto.TopicoCreateRequest;
import com.ForoHub.dto.TopicoResponse;
import com.ForoHub.dto.TopicoUpdateRequest;
import com.ForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    private final TopicoService service;
    public TopicoController(TopicoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<TopicoResponse> crear(@RequestBody @Valid TopicoCreateRequest req) {
        return ResponseEntity.ok(service.crear(req));
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponse>> listar(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue="fechaCreacion,desc") String sort) {
        String[] s = sort.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(s[1]), s[0]));
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> actualizar(@PathVariable Long id, @RequestBody @Valid TopicoUpdateRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
