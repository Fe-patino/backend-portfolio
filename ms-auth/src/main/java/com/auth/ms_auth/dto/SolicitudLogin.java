package com.auth.ms_auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SolicitudLogin {
    @NotBlank(message = "el email es obligatorio")
    @Email(message ="el email es invalido")
    private String email;

    @NotBlank(message = "la contraseña es obligatoria")
    private String password;

}
