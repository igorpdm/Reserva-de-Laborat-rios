import java.util.LinkedList;
import java.util.Queue;

public class Lista {
    private Queue<Reserva> tabelaL3;
    private Queue<Reserva> tabelaLES;

    public Lista() {
        this.tabelaL3 = new LinkedList<Reserva>();
        this.tabelaLES = new LinkedList<Reserva>();
    }

    public void insere(Reserva reserva){
        if (reserva.laboratorio.equals("L3"))
            this.tabelaL3.add(reserva);
        else
            this.tabelaLES.add(reserva);
    }

    public boolean vazia(){
        return this.tabelaL3.isEmpty() && this.tabelaLES.isEmpty();
    }

    public Reserva pesquisa(Reserva res) {
        if (res.laboratorio.equals("L3")) { //Verifica se a reserva é para o L3
            for (Reserva reserva : this.tabelaL3) { //Percorre a lista do L3
                if (reserva.horario.equals(res.horario)) { //Verifica se o horario é igual
                    return reserva; //Retorna a reserva
                }
            }
        } else {
            for (Reserva reserva : this.tabelaLES) { //Percorre a lista do LES
                if (reserva.horario.equals(res.horario)) {//Verifica se o horario é igual
                    return reserva;//Retorna a reserva
                }
            }
        }
        return null;//Retorna null caso não encontre a reserva
    }

    public Reserva retira(Reserva res) {
        if (res.laboratorio.equals("L3")) { //Verifica se a reserva é para o L3
            for (Reserva reserva : this.tabelaL3) { //Percorre a lista do L3
                if (reserva.horario.equals(res.horario)) { //Verifica se o horario é igual
                    this.tabelaL3.remove(reserva); //Remove a reserva
                    return reserva; //Retorna a reserva
                }
            }
        } else {
            for (Reserva reserva : this.tabelaLES) { //Percorre a lista do LES
                if (reserva.horario.equals(res.horario)) {//Verifica se o horario é igual
                    this.tabelaLES.remove(reserva); //Remove a reserva
                    return reserva;//Retorna a reserva
                }
            }
        }
        return null;//Retorna null caso não encontre a reserva
    }
}
