package com.ForoHub.service;

import com.ForoHub.domian.Topico;
import com.ForoHub.dto.TopicoCreateRequest;
import com.ForoHub.dto.TopicoResponse;
import com.ForoHub.dto.TopicoUpdateRequest;
import com.ForoHub.repository.TopicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {
    private final TopicoRepository repo;
    public TopicoService(TopicoRepository repo) { this.repo = repo; }

    @Transactional
    public TopicoResponse crear(TopicoCreateRequest req) {
        if (repo.existsByTituloAndMensaje(req.titulo(), req.mensaje())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico t =new Topico();
        t.setTitulo(req.titulo());
        t.setMensaje(req.mensaje());
        t.setAutor(req.autor());
        t.setCurso(req.curso());
        t.setStatus("ABIERTO");
        Topico guardado = repo.save(t);
        return toResponse(guardado);
    }

    @Transactional(readOnly = true)
    public Page<TopicoResponse> listar(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public TopicoResponse obtener(Long id) {
        Topico t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        return toResponse(t);
    }

    @Transactional
    public TopicoResponse actualizar(Long id, TopicoUpdateRequest req) {
        Topico t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        t.setTitulo(req.titulo());
        t.setMensaje(req.mensaje());
        t.setStatus(req.status());
        return toResponse(t);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Tópico no encontrado");
        repo.deleteById(id);
    }

    private TopicoResponse toResponse(Topico t) {
        return new TopicoResponse(t.getId(), t.getTitulo(), t.getMensaje(),t.getFechaCreacion(), t.getStatus(), t.getAutor(), t.getCurso());
    }
}
