// creamos una interfaz para generar metodos contratados entre clases
// con esto generamos polimorfismo y herencia
package com.duoc.dqr.interfaces;

public interface IRental {

    static final double IVA = 0.19;
    static final double DESCUENTO_CARGA = 0.07;
    static final double DESCUENTO_PASAJEROS = 0.12;

    double totalCost(); // calculamos el costo total dependiendo de los dias arrendados

    void printInvoice(); // imprimimos graficamente la boleta al usuario

}
