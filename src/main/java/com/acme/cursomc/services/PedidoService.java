package com.acme.cursomc.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.cursomc.domain.ItemPedido;
import com.acme.cursomc.domain.PagamentoComBoleto;
import com.acme.cursomc.domain.Pedido;
import com.acme.cursomc.domain.enums.EstadoPagamento;
import com.acme.cursomc.repositories.ItemPedidoRepository;
import com.acme.cursomc.repositories.PagamentoRepository;
import com.acme.cursomc.repositories.PedidoRepository;
import com.acme.cursomc.repositories.ProdutoRepository;
import com.acme.cursomc.services.exception.ObjectNofFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.getOne(id);
		if(obj == null) {
			throw new ObjectNofFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getSimpleName());
		}
		return obj;
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findById(ip.getProduto().getId()).get().getPreco());
			ip.setPedido(obj);
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
