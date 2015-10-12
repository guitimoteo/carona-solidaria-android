package br.org.carona.caronasolidaria.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diego on 12/10/2015.
 */
public class VeiculoModel {

    @SerializedName("Veiculos")
    private VeiculoEntity Veiculo;

    public void setVeiculo(VeiculoEntity Veiculo) {
        this.Veiculo = Veiculo;
    }

    public VeiculoEntity getVeiculo() {
        return Veiculo;
    }

    public static class VeiculoEntity {
        /**
         * nome : nome
         * email : asdf@fatec.com.br
         * password : asdf@fatec.com.br
         */
        @SerializedName("placa")
        private String placa;
        @SerializedName("cor")
        private String cor;
        @SerializedName("cidade")
        private String cidade;

        @SerializedName("estado")
        private String estado;
        @SerializedName("modeloDoCarro")
        private String modeloDoCarro;
        @SerializedName("marca")
        private String marca;


        public String getPlaca() {
            return placa;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }

        public String getCor() {
            return cor;
        }

        public void setCor(String cor) {
            this.cor = cor;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getModeloDoCarro() {
            return modeloDoCarro;
        }

        public void setModeloDoCarro(String modeloDoCarro) {
            this.modeloDoCarro = modeloDoCarro;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }
    }


}
