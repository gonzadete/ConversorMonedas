package Principal;

import Conversion.ConvierteMoneda;

import java.io.IOException;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        int opcion = 0;
        String moneda1 = "";
        String moneda2 = "";
        double valorAConvertir = 0;

        String[] conversion = new String[6];
        conversion[0] = "USDCLP";
        conversion[1] = "CLPUSD";
        conversion[2] = "USDBRL";
        conversion[3] = "BRLUSD";
        conversion[4] = "USDARS";
        conversion[5] = "ARSUSD";


        System.out.println("*****************************************");
        System.out.println("\nSea bienvenido al Conversor de monedas");

        String menu = """
                *** Escriba el número de la opción deseada ***
                1.- Dólar =>> Peso chileno
                2.- Peso chileno =>> Dólar
                3.- Dólar =>> Real Brasileño
                4.- Real brasileño =>> Dólar
                5.- Dólar =>> Peso argentino
                6.- Peso argentino =>> Dólar
                7.- Salir
                """;
        Scanner teclado = new Scanner(System.in);
        while (opcion != 7) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            if (opcion > 0 && opcion < 7) {
                String mon1 =  conversion[opcion - 1].substring(0,3);
                String mon2 =  conversion[opcion - 1].substring(3,6);

                System.out.println("Ingrese el valor que desea convertir: ");
                valorAConvertir = teclado.nextDouble();

                new ConvierteMoneda(mon1, mon2, valorAConvertir);
            } else if (opcion > 7) {
                System.out.println("Opción no válida");
            }else System.out.println("Proceso terminado");
        }
    }
}

