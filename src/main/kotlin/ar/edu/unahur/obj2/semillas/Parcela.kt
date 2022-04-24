package ar.edu.unahur.obj2.semillas

abstract class Parcela (var ancho: Double, var largo: Double, var horasSol: Int, var listaPlantas: MutableList<Planta> ){
    fun superficie() : Double = ancho * largo
    fun cantidadMaxima() : Int {
        if (ancho > largo) { return ( this.superficie() /5 ).toInt() }
        else { return (this.superficie() / 3 + largo).toInt() }
    }
    fun tieneComplicaciones() : Boolean {
        return listaPlantas.any{ p: Planta -> p.toleranciaAlSol() < horasSol }
    }
    fun plantar(planta: Planta){
        if (listaPlantas.size == this.cantidadMaxima() || this.horasSol >= (planta.toleranciaAlSol() + 2)){
            error("No puede plantarse esta planta en la parcela")
        }
        else { listaPlantas.add(planta) }
    }
    abstract fun seAsociaBien(planta: Planta) : Boolean

    fun porcentajeAsociadas() : Double {
        return ((this.listaPlantas.count{ p -> this.seAsociaBien(p)} * 100) / this.listaPlantas.size).toDouble()
    }
}

class ParcelaEcologica (ancho: Double, largo: Double, horasSol: Int, listaPlantas: MutableList<Planta>
    ): Parcela (ancho, largo, horasSol, listaPlantas){

     override fun seAsociaBien (planta: Planta) : Boolean {
        return (!this.tieneComplicaciones() && planta.esParcelaIdeal(this))
    }
}

class ParcelaIndustrial (ancho: Double, largo: Double, horasSol: Int, listaPlantas: MutableList<Planta>
    ): Parcela (ancho, largo, horasSol, listaPlantas) {

    override fun seAsociaBien (planta: Planta) : Boolean {
        return (planta.esFuerte() && this.listaPlantas.size == 2 )
    }
}

