package com.desafio.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.entities.Categoria;
import com.desafio.entities.ItemPedido;
import com.desafio.entities.Pedido;
import com.desafio.entities.Produto;
import com.desafio.entities.Usuario;
import com.desafio.entities.enums.CanalVendas;
import com.desafio.entities.enums.Perfil;
import com.desafio.entities.enums.Status;
import com.desafio.entities.enums.TipoCliente;
import com.desafio.repositories.CategoriaRepository;
import com.desafio.repositories.ItemPedidoRepository;
import com.desafio.repositories.PedidoRepository;
import com.desafio.repositories.ProdutoRepository;
import com.desafio.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00, "Dell", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p2 = new Produto(null, "Impressora", 800.00, "Epson", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p3 = new Produto(null, "Mouse", 80.00, "Dell", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00, "Com 02 gavetas", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p5 = new Produto(null, "Toalha", 50.00, "Banho", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p6 = new Produto(null, "Colcha", 200.00, "Casal", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p7 = new Produto(null, "TV true color", 1200.00, "LG", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p8 = new Produto(null, "Roçadeira", 800.00, "A diesel", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p9 = new Produto(null, "Abajur", 100.00, "Para quarto", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p10 = new Produto(null, "Pendente", 180.00, "Para sala", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Produto p11 = new Produto(null, "Shampoo", 90.00, "Pacote com 06", sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Usuario user1 = new Usuario(null, "Admin", "admin@gmail.com", TipoCliente.PESSOAFISICA, pe.encode("123456"),
				sdf.parse("01/01/2000 00:00"), sdf.parse("10/01/2021 21:00"), sdf.parse("10/01/2021 21:00"), true);
		user1.addPerfil(Perfil.ADMIN);
		Usuario user2 = new Usuario(null, "Nome 02", "nome02@gmail.com", TipoCliente.PESSOAFISICA, pe.encode("123456"),
				sdf.parse("01/01/2000 00:00"), sdf.parse("10/01/2021 21:00"), sdf.parse("10/01/2021 21:00"), true);
		

		usuarioRepository.saveAll(Arrays.asList(user1, user2));

		Pedido ped1 = new Pedido(null, user1, CanalVendas.E_COMMERCE, Status.AGUARDANDO_ENTREGA,
				sdf.parse("10/01/2021 21:00"), sdf.parse("10/01/2021 21:00"));
		Pedido ped2 = new Pedido(null, user2, CanalVendas.LOJA_FISICA, Status.ENTREGUE, sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Pedido ped3 = new Pedido(null, user2, CanalVendas.PARCEIROS, Status.PARCEIROS, sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Pedido ped4 = new Pedido(null, user2, CanalVendas.E_COMMERCE, Status.AGUARDANDO_ENTREGA,
				sdf.parse("10/01/2021 21:00"), sdf.parse("10/01/2021 21:00"));
		Pedido ped5 = new Pedido(null, user1, CanalVendas.LOJA_FISICA, Status.ENTREGUE, sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));
		Pedido ped6 = new Pedido(null, user1, CanalVendas.PARCEIROS, Status.PARCEIROS, sdf.parse("10/01/2021 21:00"),
				sdf.parse("10/01/2021 21:00"));

		user1.getPedidos().addAll(Arrays.asList(ped1, ped5, ped6));
		user2.getPedidos().addAll(Arrays.asList(ped2, ped3, ped4));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3, ped4, ped5));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p4, 0.00, 1, 800.00);
		ItemPedido ip4 = new ItemPedido(ped2, p7, 0.00, 1, 800.00);
		ItemPedido ip5 = new ItemPedido(ped2, p8, 100.00, 1, 800.00);
		ItemPedido ip6 = new ItemPedido(ped2, p2, 0.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		ped3.getItens().addAll(Arrays.asList(ip4));
		ped4.getItens().addAll(Arrays.asList(ip5));
		ped5.getItens().addAll(Arrays.asList(ip6));
		ped6.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		p4.getItens().addAll(Arrays.asList(ip4));
		p5.getItens().addAll(Arrays.asList(ip5));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5, ip6));
	}
}
