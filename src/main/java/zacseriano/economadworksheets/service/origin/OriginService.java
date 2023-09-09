package zacseriano.economadworksheets.service.origin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import zacseriano.economadworksheets.domain.form.OriginForm;
import zacseriano.economadworksheets.domain.mapper.OriginMapper;
import zacseriano.economadworksheets.domain.model.Origin;
import zacseriano.economadworksheets.repository.OriginRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OriginService {
	@Autowired
	private OriginRepository repository;
	@Autowired
	private OriginMapper mapper;
	@Autowired
	private OriginValidator validator;
	
	public List<Origin> listAll(){
		return repository.findAll(Sort.by(Direction.ASC, "name"));
	}
	
	public Origin create(OriginForm form) {
		Origin origin = mapper.toModel(form);
		origin = repository.save(origin);
		return origin;
	}
	
	public Origin findOrCreate(OriginForm form) {
		Origin origin = repository.findByName(form.getName());		
		if (origin == null) {
			validator.validarForm(form);
			origin = create(form);			
		}		
		return origin;
	}
	
	public Origin findByName(String name) {
		Origin origin = repository.findByName(name);		
		if (origin == null) {
			throw new ValidationException(String.format("Origin with name %s not found.", name));		
		}		
		return origin;
	}
	
	public Origin findByNameOrCreate(String name) {
		Origin origin = repository.findByName(name);		
		if (origin == null) {
			OriginForm form = OriginForm.builder().name(name).build();
			return create(form);
		}		
		return origin;
	}
}
