package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.entities.ProviderServiceEntity;

import java.util.List;

public interface IProviderServicioService {

    List<ProviderServiceEntity> getAllServicios();


    ProviderServiceEntity saveProviderService(ProviderServiceEntity servicio);

}
