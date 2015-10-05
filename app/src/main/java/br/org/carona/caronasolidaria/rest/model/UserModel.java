package br.org.carona.caronasolidaria.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model a ser serializada e enviada via json
 * User : {"nome":"nome","email":"exemplo@fatec.com.br","password":"exemplo@fatec.com.br"}
 */
public class UserModel {

    /**
     * Exemplo
     * User : {"nome":"nome","email":"exemplo@fatec.com.br","password":"exemplo@fatec.com.br"}
     */

    @SerializedName("User")
    private UserEntity User;

    public void setUser(UserEntity User) {
        this.User = User;
    }

    public UserEntity getUser() {
        return User;
    }

    public static class UserEntity {
        /**
         * nome : nome
         * email : asdf@fatec.com.br
         * password : asdf@fatec.com.br
         */
        @SerializedName("nome")
        private String nome;
        @SerializedName("email")
        private String email;
        @SerializedName("password")
        private String password;

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
