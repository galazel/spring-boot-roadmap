package com.notepad.notepad.note;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 40)
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 500)
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date dateCreated;
}
