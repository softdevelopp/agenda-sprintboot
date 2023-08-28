package com.softdevelopp.agenda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
@Data
@Entity
public class Contacto {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(length = 15)
   private Integer idcontacto;
   @Column(length = 50, columnDefinition = "varchar(50)",updatable = true,nullable = false)
   @NotBlank
   private String nombre;

   @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @NotNull
    @Past
   @Column(name="fechanac")
    private LocalDate fechaNacimiento;
    @Column(length = 50,columnDefinition = "varchar(50)",updatable = true,nullable = false)


    @NotBlank
    private String celular;

    @NotEmpty
    @Column(length = 50,columnDefinition = "varchar(50)",updatable = true,nullable = false)
   @Email
   private String email;


}
