package zacseriano.economadworksheets.service.origin;

import java.util.List;
import java.util.Optional;

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
import zacseriano.economadworksheets.shared.utils.StringUtils;

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
		Optional<Origin> optOrigin = repository.findByName(form.getName());		
		if (optOrigin.isEmpty()) {
			validator.validateForm(form);
			return create(form);			
		}		
		return optOrigin.get();
	}
	
	public Origin findByName(String name) {
		Optional<Origin> optOrigin = repository.findByName(name);		
		if (optOrigin.isEmpty()) {
			throw new ValidationException(String.format("Origin with name %s not found.", name));		
		}		
		return optOrigin.get();
	}
	
	public Origin findByNameOrCreate(String name) {
		String normalizedName = StringUtils.normalize(name);
		Optional<Origin> optOrigin = repository.findByName(normalizedName);		
		if (optOrigin.isEmpty()) {
			OriginForm form = OriginForm.builder().name(name).build();
			return create(form);
		}		
		return optOrigin.get();
	}
}
