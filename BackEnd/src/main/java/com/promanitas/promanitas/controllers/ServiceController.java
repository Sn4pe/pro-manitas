package com.promanitas.promanitas.controllers;

import com.promanitas.promanitas.entities.ServiceEntity;
import com.promanitas.promanitas.security.services.UserDetailsImpl;
import com.promanitas.promanitas.services.interfaces.IServicioService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private IServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAllServicios() {
        try {
            List<ServiceEntity> servicios = servicioService.getAllServicios();
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServicioById(@PathVariable("id") Long id) {
        Optional<ServiceEntity> servicioData = servicioService.getServicioById(id);

        return servicioData.map(servicio -> new ResponseEntity<>(servicio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ServiceEntity> createServicio(@RequestBody ServiceEntity serviceEntity) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long providerId = userDetails.getId();

            ServiceEntity newServicio = servicioService.createService(serviceEntity, providerId);
            return new ResponseEntity<>(newServicio, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, e.getMessage().equals("Provider not found") ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateServicio(@PathVariable("id") Long id, @RequestBody ServiceEntity servicio) {
        try {
            ServiceEntity updatedServicio = servicioService.updateServicio(id, servicio);
            return new ResponseEntity<>(updatedServicio, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteServicio(@PathVariable("id") Long id) {
        try {
            servicioService.deleteServicio(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('PROVIDER')")
    @GetMapping("/{id}/isOwner")
    public ResponseEntity<Boolean> isOwner(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getId();

        try {
            boolean isOwner = servicioService.isOwner(id, userId);
            return new ResponseEntity<>(isOwner, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
