package com.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.dto.UsuarioDTO;
import com.desafio.dto.UsuarioNewDTO;
import com.desafio.entities.Usuario;
import com.desafio.entities.enums.Perfil;
import com.desafio.entities.enums.TipoCliente;
import com.desafio.repositories.UsuarioRepository;
import com.desafio.security.UserSS;
import com.desafio.services.exceptions.AuthorizationException;
import com.desafio.services.exceptions.DataIntegrityException;
import com.desafio.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public Usuario find(Integer id) {

		UserSS user = UserService.authenticated();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return repo.save(obj);
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas!");
		}
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Usuario fromDto(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), null, null, null, null, null, 
				objDto.getDataAtualizacao(), objDto.isEnable());
	}

	public Usuario fromDto(UsuarioNewDTO objDto) {
		Usuario user = new Usuario(null, objDto.getNome(), objDto.getEmail(), TipoCliente.toEnum(objDto.getTipo()),
				pe.encode(objDto.getSenha()), objDto.getDataNascimento(), objDto.getDataCriacao(),
				objDto.getDataAtualizacao(), objDto.isEnable());
		return user;
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
