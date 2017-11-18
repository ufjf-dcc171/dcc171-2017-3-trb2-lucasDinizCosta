package Principal;

public class Calendario {
    /*********************************************************************************************
     * Classe responsável por controlar os dados de tempo, antes e depois do controle de pedidos *
     *********************************************************************************************/
    int dia, mes, ano;
    int horas, minutos, segundos;
    
    public Calendario(){
        dia = 0;
        mes = 0;
        ano = 0;
        horas = 0;
        minutos = 0;
        segundos = 0;
    }
    
    public Calendario(int dia, int mes, int ano, int horas, int minutos, int segundos){
        this.dia = dia;
        this.mes = mes+1;                                       ///Soma-se 1 unidade devido o mês ter inicio em 0 e não em 1
        this.ano = ano;
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }
    
    public Calendario(int dia, int mes, int ano){               ///Para auxiliar no caixaDiario
        this.dia = dia;
        this.mes = mes+1;                                       ///Soma-se 1 unidade devido o mês ter inicio em 0 e não em 1
        this.ano = ano;
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    
    public void setData(int dia, int mes, int ano, int horas, int minutos, int segundos){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }
    
    public String dataToString(){
        return dia+"/"+mes+"/"+ano;
    }
    
    public String hourToString(){
        return horas+":"+minutos+":"+segundos;
    }
}
