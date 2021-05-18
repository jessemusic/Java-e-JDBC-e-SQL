package br.com.jmccursos.spring.datajesse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jmccursos.spring.datajesse.orm.Cargo;
import br.com.jmccursos.spring.datajesse.orm.UnidadeDeTrabalho;
@Repository
public interface UnidadeDeTrabalhoRepository extends CrudRepository<UnidadeDeTrabalho, Integer> {

}
