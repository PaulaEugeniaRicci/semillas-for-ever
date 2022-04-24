package ar.edu.unahur.obj2.semillas

/*Empleo de "object" en lugar de la implementacion propia de un patron singleton, como seria en Java o C#.
Se evita el uso de instancia y constructor privado, con su respectivo metodo para obtener dicha instancia */

object Inta {
    val coleccionParcelas : MutableList<Parcela> =  mutableListOf()
    fun agregarParcela (parcela : Parcela) = this.coleccionParcelas.add(parcela)
    fun calcularPromedio() : Double {
        return (this.coleccionParcelas.sumBy { it.listaPlantas.size} / this.coleccionParcelas.size).toDouble()
    }
}
