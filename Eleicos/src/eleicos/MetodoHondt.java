package eleicos;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

class Partido {
    String nome;
    int votos;
    int mandatos;
    int divisorAtual;

    public Partido(String nome, int votos) {
        this.nome = nome;
        this.votos = votos;
        this.mandatos = 0;
        this.divisorAtual = 1;
    }

    // Calcula o próximo quociente para o método de Hondt
    public double calcularQuociente() {
        return (double) votos / divisorAtual;
    }

    // Atualiza o divisor para o próximo quociente
    public void atualizarDivisor() {
        divisorAtual++;
    }

    // Método para exibir o número de votos do partido
    public void exibirVotos() {
        System.out.println(nome + " tem " + votos + " votos.");
    }

    @Override
    public String toString() {
        return nome + ": " + mandatos + " mandatos";
    }
}

public class MetodoHondt {
    public static void main(String[] args) {
        // Lista de partidos com seus respectivos votos
        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido("Partido PSD", 1814021));
        partidos.add(new Partido("Partido CHEGA",1168836));
        partidos.add(new Partido("Partido PS", 1812469));
        partidos.add(new Partido("Partido CDS", 52992)); 
        partidos.add(new Partido("Partido IL", 319685)); 
        partidos.add(new Partido("Partido BE", 282314)); 
        partidos.add(new Partido("Partido PCP", 205436)); 
        partidos.add(new Partido("Partido L", 204676)); 
        partidos.add(new Partido("Partido PAN", 126085)); 
        partidos.add(new Partido("Partido ADN", 102132)); 
        partidos.add(new Partido("Partido RIR", 26121)); 
               
           

        int totalMandatos = 5; // Número de mandatos a serem distribuídos

        // PriorityQueue para ordenar os partidos de acordo com o quociente
        PriorityQueue<Partido> fila = new PriorityQueue<>(new Comparator<Partido>() {
            @Override
            public int compare(Partido p1, Partido p2) {
                return Double.compare(p2.calcularQuociente(), p1.calcularQuociente());
            }
        });

        // Inicializar a fila com os partidos
        fila.addAll(partidos);

        // Distribuir mandatos
        for (int i = 0; i < totalMandatos; i++) {
            Partido partido = fila.poll();
            partido.mandatos++;
            partido.atualizarDivisor();
            fila.add(partido);
        }

        // Exibir resultados
        System.out.println("Distribuicao de mandatos:");
        for (Partido partido : partidos) {
            System.out.println(partido);
        }

        // Exibir o número de votos de cada partido
        System.out.println("\nNumero de votos de cada partido:");
        for (Partido partido : partidos) {
            partido.exibirVotos();
        }

        // Afirmativas para verificar
        System.out.println("\nAfirmativas:");
        System.out.println("\"E mais vantajoso dois partidos concorrerem coligados do que separados.\"");
        System.out.println("\"O metodo de Hondt favorece os partidos maiores.\"");
    }
}
