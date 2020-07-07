package br.com.codenation;
import br.com.codenation.entities.Jogador;
import br.com.codenation.entities.MeuTime;
import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private final List<MeuTime> times = new ArrayList<>();
	private final List<Jogador> jogadores = new ArrayList<>();

	//*****TESTE OK *****
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		List<Long> idTimes = buscarTimes();
		if (!idTimes.contains(id)) {
			MeuTime time = new MeuTime(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
			times.add(time);
		} else {
			throw new IdentificadorUtilizadoException("Cadastro já efetuado");
		}
	}

	//*****TESTE OK *****
	public void incluirJogador (Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		if(jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))){
			throw new IdentificadorUtilizadoException("Identificador já utilizado");
		}
		if(times.stream().anyMatch(time -> time.getId().equals(idTime))){
			Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
			jogadores.add(jogador);
		}else{
			throw new TimeNaoEncontradoException("Time não encontrado");
		}
	}

	//*****TESTE OK *****
	public void definirCapitao(Long idJogador) {

		try {
			if(jogadores.stream().noneMatch(jogador -> jogador.getId().equals(idJogador))){
				throw new JogadorNaoEncontradoException("Jogador não encontrado");
			}
			//para cd jogador verifica e seta true para capitão
			jogadores.stream()
					.filter(jogador -> jogador.getId().equals(idJogador))
					.findFirst()
					.get()
					.setCapitao(true);

		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

	//*****TESTE OK *****
	public Long buscarCapitaoDoTime(Long idTime) {

		List<Long> listaTimes = buscarTimes();
		long capitao;

		if (listaTimes.isEmpty()){
			throw new TimeNaoEncontradoException("Time não localizado");
		}
		if(jogadores.stream().noneMatch(Jogador::getCapitao)){
			throw new CapitaoNaoInformadoException("Capitão não localizado");
		}else {
			capitao = jogadores.stream()
					.filter(jogador -> jogador.getIdTime().equals(idTime))
					.mapToLong(Jogador::getId)
					.findFirst()
					.getAsLong();
		}
			return capitao;
		}

	//*****TESTE OK *****
	public String buscarNomeJogador(Long idJogador) {

		List<Long> listaTimes = buscarTimes();
		String nomeJogador = "";
		try {
			if (listaTimes.isEmpty()) {
				throw new TimeNaoEncontradoException("Time não localizado");
			}
			if (jogadores.isEmpty()) {
				throw new JogadorNaoEncontradoException("Jogador não encontrado");
			} else {
				nomeJogador = jogadores.stream()
						.filter(jogador -> jogador.getId().equals(idJogador))
						.map(Jogador::getNome)
						.findFirst()
						.get();
			}
		} catch (IdentificadorUtilizadoException | UnsupportedOperationException e){
			e.printStackTrace();
		}
		return nomeJogador;
	}

	//*****TESTE OK *****
	public String buscarNomeTime(Long idTime) {
		
		String nome = "";
		
		try {
			if (times.stream().noneMatch(time -> time.getId().equals(idTime))) {
				throw new TimeNaoEncontradoException("Time não encontrado");
			}else{
				nome = times.stream()
					.filter(time -> time.getId().equals(idTime))
					.map(MeuTime::getNome)
					.collect(Collectors.joining());
				return nome;
			}	
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return nome;		
		
	}

	//*****TESTE OK *****
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		List<Long> listaTimes = buscarTimes();
		List<Long> jogadoresDoTime = new ArrayList<>();
		try {
			if (listaTimes.isEmpty()){
				throw new TimeNaoEncontradoException("Time não localizado");
			}else {
				jogadoresDoTime = jogadores.stream()
						.filter(jogador -> jogador.getIdTime().equals(idTime))
						.map(Jogador::getId).collect(Collectors.toList());
				return jogadoresDoTime;
			}
		} catch (IdentificadorUtilizadoException e) {
			e.printStackTrace();
		}
		return jogadoresDoTime;
	}

	//*****TESTE OK *****
	public Long buscarMelhorJogadorDoTime(Long idTime) {

		Long melhorJogador;

		if (buscarTimes().isEmpty()) {
			throw new TimeNaoEncontradoException("Time não localizado");
		}else {
			List<Jogador> idadeJogadores = jogadores.stream()
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
					.collect(Collectors.toList());
			melhorJogador = idadeJogadores.get(0).getId();
			return melhorJogador;
		}
	}

	public Long buscarJogadorMaisVelho(Long idTime) {

		Long maisVelho;

		if (buscarTimes().isEmpty()) {
			throw new TimeNaoEncontradoException("Time não localizado");
		}else {
			List<Jogador> idadeJogadores = jogadores.stream()
					.sorted(Comparator.comparing(Jogador::getDataNascimento))
					.collect(Collectors.toList());
			maisVelho = idadeJogadores.get(0).getId();
			return maisVelho;
		}
	}

	//*****TESTE OK *****
	public List<Long> buscarTimes() {

		Stream<MeuTime> timeStream = times.stream();
		return timeStream.map(MeuTime::getId).collect(Collectors.toList());
	}

	//*****TESTE OK *****
	public Long buscarJogadorMaiorSalario(Long idTime) {

		Long maiorSalario = null;

		try {
			if (buscarTimes().isEmpty()) {
				throw new TimeNaoEncontradoException("Time não localizado");
			}else {
				List<Jogador> salarios = jogadores.stream()
						.sorted(Comparator.comparing(Jogador::getSalario).reversed())
						.collect(Collectors.toList());
				maiorSalario = salarios.get(0).getId();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		return maiorSalario;
	}

	//*****TESTE OK *****
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		
		BigDecimal salarioJogador = new BigDecimal(0);
		if (jogadores.stream().noneMatch(jogador -> jogador.getId().equals(idJogador))){
			throw new JogadorNaoEncontradoException("Joagador não localizado");
		}

		for (Jogador jogador: jogadores){
			if (idJogador.equals(jogador.getId())){
				salarioJogador = jogador.getSalario();
			}
		}
		return salarioJogador;
	}

	//*****TESTE OK *****
	public List<Long> buscarTopJogadores(Integer top) {

		List<Long> topJogadores = new ArrayList<>();
		List<Long> semJogadores = new ArrayList<>();

		try {
			topJogadores = jogadores.stream()
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
					.map(Jogador::getId)
					.limit(top)
					.collect(Collectors.toList());					
								
		} catch (TimeNaoEncontradoException e) {
			e.printStackTrace();
		}
				
		if(topJogadores.isEmpty()){
			return semJogadores;
		}

		return topJogadores;
	}
}