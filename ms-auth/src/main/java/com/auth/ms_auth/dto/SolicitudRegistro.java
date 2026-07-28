package com.auth.ms_auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SolicitudRegistro {
    @NotBlank(message = "el nombre es obligatorio")
    private String nombre;

    @NotBlank(message ="el email es obligatorio")
    @Email(message="el email no es valido")
    private String email;

    @NotBlank(message = "la contraseña es obligatoria")
    private String password;

    @NotBlank(message = "el rol es obligatorio")
    private String rol;





}
