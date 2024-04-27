package org.ettarak.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ettarak.api.enums.Level;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Note implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(length = 50)
    @NotNull(message = "Title of this note can not be NULL") @NotEmpty(message = "Title of this note can not be Empty")
    private  String title;
    @NotNull(message = "Description of this note can not be NULL") @NotEmpty(message = "Description of this note can not be Empty")
    private String description;
    private Level level;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDateTime createAt;
}
