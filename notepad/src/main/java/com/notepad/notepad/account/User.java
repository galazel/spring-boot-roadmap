package com.notepad.notepad.account;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.notepad.notepad.note.Note;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Firstname should not be null.")
    @NotBlank(message = "FirstName should not be blank.")
    private String firstname;
    @NotNull(message = "Lastname should not be null.")
    @NotBlank(message = "Lastname should not be blank.")
    private String lastname;
    @NotNull(message = "Username should not be null.")
    @NotBlank(message = "Username should not be blank.")
    @Pattern(
            regexp = "^[A-Za-z][A-Za-z0-9]{4,19}$",
            message = "Username must start with a letter and be 5-20 characters long (letters and numbers only)"
    )
    private String username;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters and include uppercase, lowercase, number, and special character"
    )
    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @NotNull(message = "Email is required")
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Note> notes;

}
