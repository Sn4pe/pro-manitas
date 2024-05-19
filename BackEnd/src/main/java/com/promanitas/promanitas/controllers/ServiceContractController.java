package com.promanitas.promanitas.controllers;

import com.promanitas.promanitas.entities.ServiceContractEntity;
import com.promanitas.promanitas.security.services.UserDetailsImpl;
import com.promanitas.promanitas.services.interfaces.IServiceContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@CrossOrigin(origins = "*")
public class ServiceContractController {

    @Autowired
    private IServiceContractService serviceContractService;

    @PostMapping("/{servicioId}")
    public ResponseEntity<ServiceContractEntity> contratarServicio(@PathVariable("servicioId") Long servicioId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long clienteId = userDetails.getId();

            ServiceContractEntity contrato = serviceContractService.contratarServicio(clienteId, servicioId);
            return new ResponseEntity<>(contrato, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/my-services")
    public ResponseEntity<List<ServiceContractEntity>> getMyServices() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long clienteId = userDetails.getId();

            List<ServiceContractEntity> contratos = serviceContractService.getServicesByClientId(clienteId);
            return new ResponseEntity<>(contratos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
