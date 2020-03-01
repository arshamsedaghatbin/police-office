package com.officer.policeofiicer.service.mapper;


import com.officer.policeofiicer.domain.PoliceOfficer;
import com.police.officer.dto.PoliceOfficerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link PoliceOfficer} and its DTO {@link PoliceOfficerDTO}.
 */
@Mapper(componentModel = "spring", uses = {StolenBikerMapper.class, PoliceMapper.class})
public interface PoliceOfficerMapper extends EntityMapper<PoliceOfficerDTO, PoliceOfficer> {

    @Mapping(source = "policeOfficer.id", target = "policeOfficerId")
    @Mapping(source = "police.id", target = "policeId")
    PoliceOfficerDTO toDto(PoliceOfficer policeOfficer);

    @Mapping(source = "policeOfficerId", target = "policeOfficer")
    @Mapping(source = "policeId", target = "police")
    PoliceOfficer toEntity(PoliceOfficerDTO policeOfficerDTO);

    default PoliceOfficer fromId(Long id) {
        if (id == null) {
            return null;
        }
        PoliceOfficer policeOfficer = new PoliceOfficer();
        policeOfficer.setId(id);
        return policeOfficer;
    }
}
