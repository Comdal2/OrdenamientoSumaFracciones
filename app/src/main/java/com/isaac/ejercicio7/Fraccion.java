package com.isaac.ejercicio7;

public class Fraccion implements Comparable<Fraccion>{
    private int numerador;
    private int denominador;
    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("El denominador no puede ser cero.");
        }
        this.numerador = numerador;
        this.denominador = denominador;
    }
    // Método para obtener el numerador
    public int getNumerador() {
        return numerador;
    }

    // Método para obtener el denominador
    public int getDenominador() {
        return denominador;
    }
    // Implementación del método compareTo para ordenar las fracciones
    @Override
    public int compareTo(Fraccion otra) {
        return Integer.compare(this.numerador * otra.denominador, this.denominador * otra.numerador);
    }
    // Método para sumar fracciones
    public Fraccion add(Fraccion otra) {
        int nuevoNumerador = this.numerador * otra.denominador + otra.numerador * this.denominador;
        int nuevoDenominador = this.denominador * otra.denominador;
        return new Fraccion(nuevoNumerador, nuevoDenominador).simplify();
    }
    // Método para simplificar la fracción
    public Fraccion simplify() {
        int gcd = gcd(this.numerador, this.denominador);
        return new Fraccion(this.numerador / gcd, this.denominador / gcd);
    }
    // Método para calcular el máximo común divisor (MCD)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    // Método para representar la fracción como una cadena de texto
    @Override
    public String toString() {
        return numerador + "/" + denominador;
    }
}
