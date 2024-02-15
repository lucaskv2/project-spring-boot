package com.lucas.project.projectspringboot.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.lucas.project.projectspringboot.model.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Integer>{
    
}
