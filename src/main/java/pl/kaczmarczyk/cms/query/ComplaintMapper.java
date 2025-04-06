package pl.kaczmarczyk.cms.query;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.kaczmarczyk.cms.command.ComplaintEvent;

import java.util.List;

@Mapper
interface ComplaintMapper {

	ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

	Complaint complaintEventToComplaint(ComplaintEvent complaintEvent);

	List<ComplaintDto> map(List<Complaint> complaints);

}