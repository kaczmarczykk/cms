package pl.kaczmarczyk.cms.command;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface ComplaintMapper {

	ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

	ComplaintEvent complaintToComplaintEvent(Complaint complaint);
}