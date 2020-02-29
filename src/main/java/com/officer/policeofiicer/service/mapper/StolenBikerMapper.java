package com.officer.policeofiicer.service.mapper;


import com.officer.policeofiicer.domain.StolenBiker;
import com.police.officer.dto.StolenBikerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link StolenBiker} and its DTO {@link StolenBikerDTO}.
 */
@Mapper(componentModel = "spring", uses = {BikerMapper.class})
public interface StolenBikerMapper extends EntityMapper<StolenBikerDTO, StolenBiker> {

    @Mapping(source = "biker.id", target = "bikerId")
    StolenBikerDTO toDto(StolenBiker stolenBiker);

    @Mapping(source = "bikerId", target = "biker")
    StolenBiker toEntity(StolenBikerDTO stolenBikerDTO);

    default StolenBiker fromId(Long id) {
        if (id == null) {
            return null;
        }
        StolenBiker stolenBiker = new StolenBiker();
        stolenBiker.setId(id);
        return stolenBiker;
    }
}
