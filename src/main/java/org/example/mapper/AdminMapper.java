package org.example.mapper;

import org.example.dto.AdminDTO;
import org.example.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    List<AdminDTO> toDTOList(List<Admin> adminList);

    AdminDTO toDTO(Admin admin);
    Admin toEntity(AdminDTO adminDTO);
}
