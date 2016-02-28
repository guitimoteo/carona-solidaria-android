package br.org.carona.caronasolidaria.rest.model;

/**
 * Created by Guilherme on 28/02/16.
 */
public class PedidoModel {

    /**
     * Pedido : {"id":"1","user_id":"9","carona_id":"1","aceito":null,"created":null}
     * User : {"email":"email@example.com","id":"9","nome":"nome"}
     * Carona : {"id":"1"}
     */

    private PedidoEntity Pedido;
    private UserEntity User;
    private CaronaEntity Carona;

    public void setPedido(PedidoEntity Pedido) {
        this.Pedido = Pedido;
    }

    public void setUser(UserEntity User) {
        this.User = User;
    }

    public void setCarona(CaronaEntity Carona) {
        this.Carona = Carona;
    }

    public PedidoEntity getPedido() {
        return Pedido;
    }

    public UserEntity getUser() {
        return User;
    }

    public CaronaEntity getCarona() {
        return Carona;
    }

    public static class PedidoEntity {
        /**
         * id : 1
         * user_id : 9
         * carona_id : 1
         * aceito : null
         * created : null
         */

        private String id;
        private String user_id;
        private String carona_id;
        private Boolean aceito;
        private Boolean created;

        public void setId(String id) {
            this.id = id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setCarona_id(String carona_id) {
            this.carona_id = carona_id;
        }

        public void setAceito(Boolean aceito) {
            this.aceito = aceito;
        }

        public void setCreated(Boolean created) {
            this.created = created;
        }

        public String getId() {
            return id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getCarona_id() {
            return carona_id;
        }

        public Boolean getAceito() {
            return aceito;
        }

        public Boolean getCreated() {
            return created;
        }
    }

    public static class UserEntity {
        /**
         * email : email@example.com
         * id : 9
         * nome : nome
         */

        private String email;
        private String id;
        private String nome;

        public void setEmail(String email) {
            this.email = email;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public String getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }
    }

    public static class CaronaEntity {
        /**
         * id : 1
         */

        private String id;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
