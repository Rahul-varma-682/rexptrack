package com.rahul.rexptrack.dto.response;
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String email;
    private String role;
    private Boolean isPremium;

    public AuthResponse() {
    }

    public AuthResponse(String token, Long id, String name, String email, String role, Boolean isPremium) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.isPremium = isPremium;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public Boolean isPremium() {
        return isPremium;
    }

    public void setPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private Long id;
        private String name;
        private String email;
        private String role;
        private Boolean isPremium;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder isPremium(Boolean isPremium) {
            this.isPremium = isPremium;
            return this;
        }
        public AuthResponse build() {
            return new AuthResponse(token, id, name, email, role, isPremium);
        }
    }
}

