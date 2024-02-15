package com.lucas.project.projectspringboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.lucas.project.projectspringboot.exception.ResourceNotFoundException;
import com.lucas.project.projectspringboot.model.dto.ClienteDto;
import com.lucas.project.projectspringboot.model.entity.Cliente;
import com.lucas.project.projectspringboot.model.payload.MensajeResponse;
import com.lucas.project.projectspringboot.service.IClienteService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1")
public class clienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll(){
        List<Cliente> getList = clienteService.listAll();
        if (getList == null || getList.isEmpty()) {
            throw new ResourceNotFoundException("clientes");
        }
        
        
        return new ResponseEntity<>(MensajeResponse.builder()
                                            .mensaje("")
                                            .object(getList).
                                            build()
                                    , HttpStatus.OK);        
    }

    @PostMapping("cliente") 
    public ResponseEntity<?> create(@RequestBody @Valid ClienteDto clienteDto){ //@RequestBody: se utiliza para indicar que un método de controlador espera que el cuerpo de la solicitud HTTP se convierta automáticamente en un objeto Java.
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(ClienteDto.builder()
                                .idCliente(clienteSave.getIdCliente())
                                .nombre(clienteSave.getNombre())
                                .apellido(clienteSave.getApellido())
                                .correo(clienteSave.getCorreo())
                                .fechaRegistro(clienteSave.getFechaRegistro())
                                .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MensajeResponse.builder()
                                                        .mensaje(exDt.getMessage())
                                                        .object(null)
                                                        .build()
                                        , HttpStatus.METHOD_NOT_ALLOWED);
        }
        
        
    }
    
    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id){
        Cliente clienteUpdate = null;

            try {
                if (clienteService.existsById(id)) {
                    clienteDto.setIdCliente(id);
                    clienteUpdate = clienteService.save(clienteDto);
                    return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado Correctamente")
                        .object(ClienteDto.builder()
                                    .idCliente(clienteUpdate.getIdCliente())
                                    .nombre(clienteUpdate.getNombre())
                                    .apellido(clienteUpdate.getApellido())
                                    .correo(clienteUpdate.getCorreo())
                                    .fechaRegistro(clienteUpdate.getFechaRegistro())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
                }else{
                    throw new ResourceNotFoundException("cliente", "id", id);
                }
                
    
                
            } catch (DataAccessException exDt) {
                return new ResponseEntity<>(MensajeResponse.builder()
                                                            .mensaje(exDt.getMessage())
                                                            .object(null)
                                                            .build()
                                            , HttpStatus.INTERNAL_SERVER_ERROR);
            }
        
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){ //@PathVariable: indica que este id es una variable en forma de parametro 
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(MensajeResponse.builder()
                                                        .mensaje(exDt.getMessage())
                                                        .object(null)
                                                        .build()
                                        , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            throw new ResourceNotFoundException("cliente", "id", id);
        }
        
        
        return new ResponseEntity<>(MensajeResponse.builder()
                                        .mensaje("")
                                        .object(ClienteDto.builder()
                                                        .idCliente(cliente.getIdCliente())
                                                        .nombre(cliente.getNombre())
                                                        .apellido(cliente.getApellido())
                                                        .correo(cliente.getCorreo())
                                                        .fechaRegistro(cliente.getFechaRegistro())
                                                        .build())
                                        .build()
                                    , HttpStatus.OK);        
    }
}
