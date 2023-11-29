import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ReservaDeLaboratorio {
    private static Map<String, Lista> laboratorios = new HashMap<>();
    private static Map<String, ArrayList<Reserva>> reservasPendentes = new HashMap<>();

    public static void main(String[] args) {
        addReserva("M1", "L3", "8:40");
        addReserva("M1", "L3", "10:35");
        addReserva("M1", "LES", "13:00");
        addReserva("M2", "LES", "14:40");
        addReserva("M2", "L3", "16:35");
        desfazerReserva("M1");
        desfazerReserva("M2");
        addReserva("M2", "LES", "16:35");
        salvaReservas("M1");
        salvaReservas("M2");
        imprimirReservas();
    }

    public static void addReserva(String monitor, String laboratorio, String horario) {
        if (reservasPendentes.containsKey(monitor)) { //Verifica se o monitor já tem reservas pendentes
            ArrayList<Reserva> reservas = reservasPendentes.get(monitor); //Pega as reservas pendentes
            for (Reserva reserva : reservas) { //Percorre as reservas pendentes
                if (reserva.horario.equals(horario)) { //Verifica se o horario é igual
                    throw new IllegalArgumentException("Monitor já tem uma reserva neste horário."); //Lança uma exceção
                }
            }
            reservas.add(new Reserva(horario, monitor, laboratorio)); //Adiciona a nova reserva
            reservasPendentes.put(monitor, reservas); //Atualiza as reservas pendentes
        } else {
            ArrayList<Reserva> reservas = new ArrayList<>(); //Cria uma lista de reservas
            reservas.add(new Reserva(horario, monitor, laboratorio)); //Adiciona a nova reserva
            reservasPendentes.put(monitor, reservas); //Atualiza as reservas pendentes
        }
    }

    public static void salvaReservas(String monitor) {
        ArrayList<Reserva> reservas = reservasPendentes.get(monitor); //Pega as reservas pendentes
        reservasPendentes.remove(monitor); //Remove as reservas pendentes
        for (Reserva reserva : reservas) { //Percorre as reservas pendentes
            if (laboratorios.containsKey(reserva.horario)) { //Verifica se o horario já tem reservas
                laboratorios.get(reserva.horario).insere(reserva); //Adiciona a reserva
            } else {
                Lista lista = new Lista(); //Cria uma lista
                lista.insere(reserva); //Adiciona a reserva
                laboratorios.put(reserva.horario, lista); //Atualiza as reservas
            }
        }
    }

    public static void desfazerReserva(String monitor) {
        for (Map.Entry<String, ArrayList<Reserva>> entry : reservasPendentes.entrySet()) { //Percorre as reservas pendentes
            String laboratorio = entry.getKey(); //Pega o laboratorio
            ArrayList<Reserva> reservas = entry.getValue(); //Pega as reservas
            for (int i = reservas.size() - 1; i >= 0; i--) { //Percorre as reservas
                Reserva reserva = reservas.get(i); //Pega a reserva
                if (reserva.monitor.equals(monitor)) { //Verifica se a reserva é do monitor
                    reservas.remove(i); //Remove a reserva
                    reservasPendentes.put(laboratorio, reservas); //Atualiza as reservas pendentes
                    return;
                }
            }
        }
    }

    public static void imprimirReservas() {
        System.out.println("\t\tL3\tLES");
        for (String horario : laboratorios.keySet()) {
            System.out.print(horario + "\t");
            Lista lista = laboratorios.get(horario);
            Reserva reservaL3 = lista.pesquisa(new Reserva(horario, "", "L3"));
            Reserva reservaLES = lista.pesquisa(new Reserva(horario, "", "LES"));
            System.out.print((reservaL3 != null ? reservaL3.monitor : "") + "\t");
            System.out.println(reservaLES != null ? reservaLES.monitor : "");
        }
    }


}
