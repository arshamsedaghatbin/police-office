package com.officer.policeofiicer.service.mapper;


import com.officer.policeofiicer.domain.Police;
import com.officer.policeofiicer.service.dto.PoliceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Police} and its DTO {@link PoliceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PoliceMapper extends EntityMapper<PoliceDTO, Police> {


    @Mapping(target = "ofiicers", ignore = true)
    @Mapping(target = "removeOfiicer", ignore = true)
    Police toEntity(PoliceDTO policeDTO);

    default Police fromId(Long id) {
        if (id == null) {
            return null;
        }
        Police police = new Police();
        police.setId(id);
        return police;
    }
}
