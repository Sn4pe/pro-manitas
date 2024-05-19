package com.promanitas.promanitas.controllers;

import com.promanitas.promanitas.entities.ProviderEntity;
import com.promanitas.promanitas.services.interfaces.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/provider")
public class ProviderController {
    @Autowired
    private IProviderService providerService;

    @GetMapping("/all")
    public ResponseEntity<List<ProviderEntity>> allAccess() {
        try {
            List<ProviderEntity> providers = providerService.getAllProviders();
            return new ResponseEntity<>(providers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/{userId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ProviderEntity> createProvider(
            @PathVariable(value = "userId") Long userId,
            @RequestBody ProviderEntity request) {
        try {
            ProviderEntity newProvider = providerService.createProviderFromCustomer(
                    userId,
                    request.getDescripcion_servicio(),
                    request.getDisponibilidad());
            return new ResponseEntity<>(newProvider, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderEntity> getProviderById(@PathVariable("id") long id) {
        Optional<ProviderEntity> providerData = providerService.getProviderById(id);

        return providerData.map(provider -> new ResponseEntity<>(provider, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{userId}/isProvider")
    public ResponseEntity<?> checkIfProvider(@PathVariable Long userId) {
        boolean isProvider = providerService.isProvider(userId);
        return ResponseEntity.ok(Collections.singletonMap("isProvider", isProvider));
    }
    
    @PutMapping("/update/{userId}")
    public ResponseEntity<ProviderEntity> updateProvider(@PathVariable Long userId, @RequestBody ProviderEntity provider) {
      
        try {
        	
        	ProviderEntity providerResponse = providerService.updateProvider(userId, provider);
            return new ResponseEntity<>(providerResponse, HttpStatus.CREATED);
            
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
