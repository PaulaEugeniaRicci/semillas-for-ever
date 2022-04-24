package ar.edu.unahur.obj2.semillas

/*Empleo de "object" en lugar de la implementacion propia de un patron singleton, como seria en Java o C#.*/

object Inta {
    val coleccionParcelas : MutableList<Parcela> =  mutableListOf()
    fun agregarParcela (parcela : Parcela) = this.coleccionParcelas.add(parcela)
    fun calcularPromedio() : Int {      //  en clase eligieron el retorno como entero en vez de double
        if (this.coleccionParcelas.size > 0)
        return (this.coleccionParcelas.sumBy { it.listaPlantas.size} / this.coleccionParcelas.size)
        else return 0
    }
    fun parcelaMasAutosustentable() : Parcela? {
        val parcelasMayorACuatro = coleccionParcelas.filter { it.listaPlantas.size > 4 }
        return parcelasMayorACuatro.maxByOrNull { it.porcentajeAsociadas() }
    }
}
