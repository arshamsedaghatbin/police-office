package com.officer.policeofiicer.service.mapper;


import com.officer.policeofiicer.domain.Biker;
import com.officer.policeofiicer.service.dto.BikerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Biker} and its DTO {@link BikerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BikerMapper extends EntityMapper<BikerDTO, Biker> {


    @Mapping(target = "stolenBikes", ignore = true)
    @Mapping(target = "removeStolenBike", ignore = true)
    Biker toEntity(BikerDTO bikerDTO);

    default Biker fromId(Long id) {
        if (id == null) {
            return null;
        }
        Biker biker = new Biker();
        biker.setId(id);
        return biker;
    }
}
