package com.app.work;


import java.util.Optional;

class Employee {
    private String name;
    private String email;

    // Constructor
    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Wrap email in Optional
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public String getName() {
        return name;
    }
}


