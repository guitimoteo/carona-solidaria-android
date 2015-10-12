package br.org.carona.caronasolidaria.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diego on 12/10/2015.
 */
public class AgendamentoModel {


    @SerializedName("Agendamento")
    private AgendamentoEntity Agendamento;

    public void setUser(AgendamentoEntity Agendamento) {
        this.Agendamento = Agendamento;
    }

    public AgendamentoEntity getAgendamento() {
        return Agendamento;
    }

    public static class AgendamentoEntity {


        @SerializedName("diaDaSemana")
        private String diaDaSemana;
        @SerializedName("horarioDePartida")
        private String horarioDePartida;
        @SerializedName("horarioDeSaida")
        private String horarioDeSaida;
        @SerializedName("carona_id")
        private int carona_id;

        public String getDiaDaSemana() {
            return diaDaSemana;
        }

        public void setDiaDaSemana(String diaDaSemana) {
            this.diaDaSemana = diaDaSemana;
        }

        public String getHorarioDePartida() {
            return horarioDePartida;
        }

        public void setHorarioDePartida(String horarioDePartida) {
            this.horarioDePartida = horarioDePartida;
        }

        public String getHorarioDeSaida() {
            return horarioDeSaida;
        }

        public void setHorarioDeSaida(String horarioDeSaida) {
            this.horarioDeSaida = horarioDeSaida;
        }

        public int getCarona_id() {
            return carona_id;
        }

        public void setCarona_id(int carona_id) {
            this.carona_id = carona_id; }

    }
}