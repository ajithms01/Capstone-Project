package com.example.Management.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Employee {
    @Id
    private Long employeeId;
    private String employeeName;
    private String employeeEmail;
    private String password;
    private String username;
    private EmployeeRoles role;
}
