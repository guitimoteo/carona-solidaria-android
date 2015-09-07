package br.org.carona.caronasolidaria.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilherme on 07/09/15.
 */
public class CaronaModel {
    /**
     * carona : {"id":"1","pontoInicial":"inicio","horarioDePartida":"00:00:00","horarioDeSaida":"00:00:00","incialLatitude":0,"incialLongitude":0}
     */

    @SerializedName("carona")
    private CaronaEntity carona;

    public void setCarona(CaronaEntity carona) {
        this.carona = carona;
    }

    public CaronaEntity getCarona() {
        return carona;
    }

    public static class CaronaEntity {
        /**
         * id : 1
         * pontoInicial : inicio
         * horarioDePartida : 00:00:00
         * horarioDeSaida : 00:00:00
         * incialLatitude : 0.0
         * incialLongitude : 0.0
         */

        private String id;
        private String pontoInicial;
        private String horarioDePartida;
        private String horarioDeSaida;
        private double incialLatitude;
        private double incialLongitude;

        public void setId(String id) {
            this.id = id;
        }

        public void setPontoInicial(String pontoInicial) {
            this.pontoInicial = pontoInicial;
        }

        public void setHorarioDePartida(String horarioDePartida) {
            this.horarioDePartida = horarioDePartida;
        }

        public void setHorarioDeSaida(String horarioDeSaida) {
            this.horarioDeSaida = horarioDeSaida;
        }

        public void setIncialLatitude(double incialLatitude) {
            this.incialLatitude = incialLatitude;
        }

        public void setIncialLongitude(double incialLongitude) {
            this.incialLongitude = incialLongitude;
        }

        public String getId() {
            return id;
        }

        public String getPontoInicial() {
            return pontoInicial;
        }

        public String getHorarioDePartida() {
            return horarioDePartida;
        }

        public String getHorarioDeSaida() {
            return horarioDeSaida;
        }

        public double getIncialLatitude() {
            return incialLatitude;
        }

        public double getIncialLongitude() {
            return incialLongitude;
        }
    }

}
