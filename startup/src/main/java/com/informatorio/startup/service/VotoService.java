package com.informatorio.startup.service;


import com.informatorio.startup.dto.VotoDTO;
import com.informatorio.startup.entity.Emprendimiento;
import com.informatorio.startup.entity.Voto;
import com.informatorio.startup.repository.EmprendimientoRepository;
import com.informatorio.startup.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
------------
INICIO DEL SERVICIO
------------
 */
@Service
public class VotoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final Converter<VotoDTO, Voto> votoDTOVotoConverter;
    private final VotoRepository votoRepository;
    //SE APLICAN LAS DEPENDENCIAS
    @Autowired
    public VotoService(EmprendimientoRepository emprendimientoRepository, Converter<VotoDTO, Voto> votoDTOVotoConverter, VotoRepository votoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.votoDTOVotoConverter = votoDTOVotoConverter;
        this.votoRepository = votoRepository;
    }

    /*
    ------------
    ACCIONES DEL SERVICIO
    ------------
     */
//CONTROLAR LOS VOTOS
    public boolean controlarVoto(VotoDTO votoDTO){
        Voto voto = votoDTOVotoConverter.convert(votoDTO);
        return votoRepository.findAll().stream().anyMatch(v -> {
            assert voto != null;
            return v.getUsuarioId().equals(voto.getUsuarioId()) && v.getEmprendimientoId().equals(voto.getEmprendimientoId());
        });
    }

    //CREAR UN VOTO
    public Boolean crearVoto(VotoDTO votoDTO) {
        Voto voto = votoDTOVotoConverter.convert(votoDTO);
        if(!controlarVoto(votoDTO)){
            assert voto != null;
            Emprendimiento emprendimiento = emprendimientoRepository.getById(voto.getEmprendimientoId());
            emprendimiento.setContadorVotos(emprendimiento.getContadorVotos()+1);
            emprendimientoRepository.save(emprendimiento);
            votoRepository.save(voto);
            return true;
        }
        return false;
    }

    //OBTENER LOS VOTOS
    public List<Voto> obtenerVotos(Long usuarioId) {
        return votoRepository.findByUsuarioId(usuarioId);
    }

//OBTENER RANKING POR VOTOS

}