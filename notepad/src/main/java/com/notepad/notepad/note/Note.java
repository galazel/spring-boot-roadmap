package com.notepad.notepad.note;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.notepad.notepad.account.User;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 40)
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 500)
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
}
