package com.informatorio.startup.converter;

import com.informatorio.startup.dto.VotoDTO;
import com.informatorio.startup.entity.Voto;
import com.informatorio.startup.repository.EmprendimientoRepository;
import com.informatorio.startup.repository.UsuarioRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VotoDTOConverter implements Converter<VotoDTO, Voto> {
    public VotoDTOConverter(EmprendimientoRepository emprendimientoRepository,
                            UsuarioRepository usuarioRepository) {
    }
    @Override
    public Voto convert(VotoDTO votoDto) {
        Voto voto = new Voto();
        voto.setGenerado(votoDto.getGenerado());
        voto.setUsuarioId(votoDto.getUsuarioId());
        voto.setEmprendimientoId(votoDto.getEmprendimientoId());
        voto.setFechaDeCreacion(LocalDateTime.now());
        return voto;
    }
}