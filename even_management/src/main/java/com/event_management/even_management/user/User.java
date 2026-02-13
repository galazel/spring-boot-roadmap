package com.event_management.even_management.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name must be at most 50 characters")
        private String lastname;

        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name must be at most 50 characters")
        private String firstname;

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Contact is required")
        @Pattern(regexp = "^[0-9]{10,15}$", message = "Contact must be 10-15 digits")
        private String contact;

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be 6-100 characters")
        private String password;

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
        private String username;

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Role is required")
        private Role role;

        public enum Role {
            CLIENT, ORGANIZER
        }

}
