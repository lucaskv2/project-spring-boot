package com.lucas.project.projectspringboot.service;


import java.util.List;

import com.lucas.project.projectspringboot.model.dto.ClienteDto;
import com.lucas.project.projectspringboot.model.entity.Cliente;

public interface IClienteService {
    List<Cliente> listAll();

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);
}
