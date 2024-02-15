package com.lucas.project.projectspringboot.model.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data 
@ToString
@Builder
public class ClienteDto implements Serializable{

	private Integer idCliente;
    @NotEmpty(message = "Nombre Requeridp")
    @Size(min = 2, max = 25)
    private String nombre;
    @NotEmpty(message = "Apellido Requeridp")
    @Size(min = 2, max = 25)
    private String apellido;
    @NotEmpty(message = "Correo Requerido")
    @Email
    private String correo;
    private Date fechaRegistro;


}
