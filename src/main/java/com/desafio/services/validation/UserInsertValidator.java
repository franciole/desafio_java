package com.desafio.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.dto.UsuarioNewDTO;
import com.desafio.entities.Usuario;
import com.desafio.repositories.UsuarioRepository;
import com.desafio.resources.excepion.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UsuarioNewDTO> {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Usuario aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}