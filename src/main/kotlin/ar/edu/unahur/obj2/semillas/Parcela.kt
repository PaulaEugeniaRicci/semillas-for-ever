package ar.edu.unahur.obj2.semillas

class Parcela (var ancho: Double, var largo: Double, var horasSol: Int, var listaPlantas: MutableList<Planta>, private val strategy: Strategy){
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

    fun ejecutarStrategy(planta: Planta) = strategy.seAsociaBien(this, planta)
}