package com.promanitas.promanitas.controllers;

import com.promanitas.promanitas.entities.ServiceEntity;
import com.promanitas.promanitas.security.services.UserDetailsImpl;
import com.promanitas.promanitas.services.ServicioService;
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
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    ServicioService servicioService;


    @GetMapping("/all")
    public ResponseEntity<List<ServiceEntity>> allAccess() {
        try {
            List<ServiceEntity> providers = servicioService.getAllServicios();
            return new ResponseEntity<>(providers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('PROVIDER')")
    public ResponseEntity<ServiceEntity> createService(@RequestBody ServiceEntity serviceEntity) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long providerId = userDetails.getId();

            ServiceEntity service = servicioService.createService(serviceEntity, providerId);
            return new ResponseEntity<>(service, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, e.getMessage().equals("Provider not found") ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getTutorialById(@PathVariable("id") long id) {
        Optional<ServiceEntity> serviceData = servicioService.getServicioById(id);

        return serviceData.map(service -> new ResponseEntity<>(service, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}