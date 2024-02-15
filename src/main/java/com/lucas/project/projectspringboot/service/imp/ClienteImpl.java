package com.lucas.project.projectspringboot.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.project.projectspringboot.model.entity.Cliente;
import com.lucas.project.projectspringboot.service.IClienteService;
import com.lucas.project.projectspringboot.model.dao.IClienteDao;
import com.lucas.project.projectspringboot.model.dto.ClienteDto;

@Service //se usa para construir una clase de Servicio  que se conecta a varios repositorios y agrupa funcionalidad.
public class ClienteImpl implements IClienteService{

    @Autowired
    private IClienteDao clienteDao;

    @Transactional(readOnly = true)
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                            .idCliente(clienteDto.getIdCliente())
                            .nombre(clienteDto.getNombre())
                            .apellido(clienteDto.getApellido())
                            .correo(clienteDto.getCorreo())
                            .fechaRegistro(clienteDto.getFechaRegistro())
                            .build();
        return clienteDao.save(cliente);
    }
    
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public boolean existsById(Integer id) {
        return clienteDao.existsById(id);
    }

    @Override
    public List<Cliente> listAll() {
        return (List<Cliente>) clienteDao.findAll();
    }
    
}
