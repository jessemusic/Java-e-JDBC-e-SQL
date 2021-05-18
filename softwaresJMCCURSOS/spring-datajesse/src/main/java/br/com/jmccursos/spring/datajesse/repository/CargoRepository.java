package br.com.jmccursos.spring.datajesse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jmccursos.spring.datajesse.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo,Integer>{

}
