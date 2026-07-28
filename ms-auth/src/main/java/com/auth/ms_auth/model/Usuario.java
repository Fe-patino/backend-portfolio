package com.auth.ms_auth.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "el nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre; 

    @NotBlank(message = "el email es obligatorio")
    @Email(message = "el email no es valido")
    @Column(nullable= false, unique = true,length = 150)
    private String email;

    @NotBlank(message = "la contraseña es obligatoria")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol;

    public enum Rol{
        CLIENTE, RESTAURANTE, REPARTIDOR, ADMIN
    }


    

}
